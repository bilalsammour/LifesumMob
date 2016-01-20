package com.lifesum.lifesummob.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * A utility class to do all operations in {@link SharedPreferences}
 */
public class PrefManager {

    private static SharedPreferences sharedPreferences;

    /**
     * A private conductor because this class is
     * a utility class
     */
    private PrefManager() {
        // It is required to be private
    }

    private static SharedPreferences newPreferenceInstance(Context context) {
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences(
                    context.getApplicationContext()
                            .getPackageName(), Context.MODE_PRIVATE);

        return sharedPreferences;
    }

    /**
     * @param context the parent context
     * @param key     the name of the preference to modify
     * @param value   the new value for the preference
     */
    public static void putObject(Context context, String key, Object value) {
        SharedPreferences pref = newPreferenceInstance(context);

        SharedPreferences.Editor editor = pref.edit();

        String jsonObject;
        jsonObject = Parser.parseToJson(value, Object.class);
        editor.putString(key, jsonObject);
        editor.apply();
    }

    /**
     * Retrieve an object value of type {@link T}
     *
     * @param context    the parent context
     * @param key        the name of the preference to retrieve
     * @param classOfT   the class of {@link T}
     * @param defaultVal the value to return if the preference
     *                   is not exist
     * @param <T>        the type of the desired object
     * @return the preference value of type {@link T} if it exists,
     * or {@code defaultVal}.
     */
    /**
     * Retrieve an object value of type {@link T}
     *
     * @param context  the parent context
     * @param key      the name of the preference to retrieve
     * @param classOfT the class of {@link T}
     * @param <T>      the type of the desired object
     * @return the preference value of type {@link T} if it exists,
     * or {@code defaultVal}.
     */
    public static <T> T getObject(Context context, String key, Class<T> classOfT) {
        return getObject(context, key, classOfT, null);
    }

    /**
     * Retrieve an object value of type {@link T}
     *
     * @param context    the parent context
     * @param key        the name of the preference to retrieve
     * @param classOfT   the class of {@link T}
     * @param defaultVal the value to return if the preference
     *                   is not exist
     * @param <T>        the type of the desired object
     * @return the preference value of type {@link T} if it exists,
     * or {@code defaultVal}.
     */
    public static <T> T getObject(Context context, String key,
                                  Class<T> classOfT, T defaultVal) {
        SharedPreferences pref = newPreferenceInstance(context);

        String jsonString = pref.getString(key, null);
        if (jsonString == null) {
            return defaultVal;
        }

        T t;
        t = Parser.parseFromJson(jsonString, classOfT);

        return t;
    }

    /**
     * Remove an object with a specific key
     *
     * @param context the parent context
     * @param key     the name of the preference to retrieve
     */
    public static void removeObject(Context context, String key) {
        SharedPreferences pref = newPreferenceInstance(context);

        SharedPreferences.Editor editor = pref.edit();

        editor.remove(key);
        editor.apply();
    }

    /**
     * Remove all objects
     *
     * @param context the parent context
     */
    public static void clear(Context context) {
        SharedPreferences pref = newPreferenceInstance(context);

        SharedPreferences.Editor editor = pref.edit();

        editor.clear();
        editor.apply();
    }
}
