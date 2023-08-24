package com.example.quiz.network;

import retrofit2.Call;
import retrofit2.http.GET;
import com.example.quiz.model.ApiResponse;

public interface ApiService {

    @GET("https://jsonplaceholder.typicode.com/photos")
    Call<ApiResponse> getData();
}
