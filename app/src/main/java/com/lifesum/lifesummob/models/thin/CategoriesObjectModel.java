package com.lifesum.lifesummob.models.thin;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * The object that contains the list
 */
public class CategoriesObjectModel implements Serializable {

    @SerializedName("food")
    private List<FoodModel> food;

    public List<FoodModel> getFood() {
        return food;
    }
}
