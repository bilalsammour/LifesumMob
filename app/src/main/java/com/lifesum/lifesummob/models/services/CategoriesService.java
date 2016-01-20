package com.lifesum.lifesummob.models.services;

import com.lifesum.lifesummob.models.thin.CategoriesObjectModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Categories service for the API
 */
public interface CategoriesService {

    @Headers(ServiceConstants.AUTHORIZATION_HEADER)
    @GET(ServiceConstants.BASE_SUFFIX_URL + "/{search}")
    Call<CategoriesObjectModel> getCategories(@Path("search") String search);
}
