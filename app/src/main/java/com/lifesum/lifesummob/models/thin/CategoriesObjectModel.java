package com.lifesum.lifesummob.models.thin;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * The object that contains the list
 */
public class CategoriesObjectModel implements Serializable {

    @SerializedName("serving_categories")
    private List<CategoryModel> categories;

    public List<CategoryModel> getCategories() {
        return categories;
    }
}
