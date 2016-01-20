package com.jamalon.ereader.models.services;

import com.jamalon.ereader.models.thin.BookModel;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

public interface BooksService {

    @GET("/books")
    Call<List<BookModel>> getBooks();
}
