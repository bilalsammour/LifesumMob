package com.jamalon.ereader.models.thin;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.NumberFormat;

public class BookModel implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private double price;

    @SerializedName("newPrice")
    private double newPrice;

    @SerializedName("currency")
    private String currency;

    @SerializedName("imageUrl")
    private String imageUrl;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceWithCurrency() {
        NumberFormat nf = NumberFormat.getInstance();
        String priceStr = nf.format(getPrice());

        return priceStr + " " + getCurrency();
    }

    public double getNewPrice() {
        return newPrice;
    }

    public String getNewPriceWithCurrency() {
        NumberFormat nf = NumberFormat.getInstance();
        String priceStr = nf.format(getNewPrice());

        return priceStr + " " + getCurrency();
    }

    public String getCurrency() {
        return currency;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
