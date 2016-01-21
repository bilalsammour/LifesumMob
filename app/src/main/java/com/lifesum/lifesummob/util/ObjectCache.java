package com.lifesum.lifesummob.util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class is for caching objects
 */
public class ObjectCache {

    /**
     * Cache an object to the storage
     *
     * @param context the parent context
     * @param key     the name of the file to open; can not contain path separators
     * @param object  the object to write to the target stream
     * @throws IOException if an error occurs while writing to the target stream
     */
    public static void writeObject(Context context, String key, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    /**
     * Get the cached object by its key
     *
     * @param context the parent context
     * @param key     the name of the file to open; can not contain path separators
     * @param <T>     the type of the object
     * @return the cached object
     * @throws IOException            if an error occurs while writing to the target stream
     * @throws ClassNotFoundException if the class of one of the objects
     *                                in the object graph cannot be found
     */
    @SuppressWarnings("unchecked")
    public static <T> T readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);

        return (T) ois.readObject();
    }
}
