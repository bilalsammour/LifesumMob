package com.lifesum.lifesummob.util;

import com.google.gson.Gson;

/**
 * This class is for parsing from and to JSON.
 */
public class Parser {

    private static Gson gson = new Gson();

    /**
     * A private conductor because this class is
     * a utility class
     */
    private Parser() {
        // It is required to be private
    }

    /**
     * JSON to object
     *
     * @param json     the string from which the object is to be parsed
     * @param classOfT the class of {@link T}
     * @param <T>      the type of the desired object
     * @return an object of type {@link T} from the string.
     * Returns {@code null} if {@code json} is {@code null}.
     */
    public static <T> T parseFromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * @param src      the object of type {@link T} for which JSON
     *                 representation is to be created
     * @param classOfT the class of {@link T}
     * @param <T>      the type of the desired object
     * @return JSON representation of {@code src} with type {@link String}.
     */
    public static <T> String parseToJson(T src, Class<T> classOfT) {
        return gson.toJson(src, classOfT);
    }
}
