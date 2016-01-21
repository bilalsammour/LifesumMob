package com.lifesum.lifesummob.models.thin;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * The food object from the API
 */
@SuppressWarnings("unused")
public class FoodModel implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("category")
    private String category;

    @SerializedName("fat")
    private double fat;

    @SerializedName("calories")
    private int calories;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public double getFat() {
        return fat;
    }

    public int getCalories() {
        return calories;
    }
}
