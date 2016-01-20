package com.lifesum.lifesummob.models.thin;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * The category object from the API
 */
public class CategoryModel implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("source")
    private int source;

    @SerializedName("old")
    private int old;

    public String getName() {
        return name;
    }

    public int getSource() {
        return source;
    }

    public int getOld() {
        return old;
    }
}
