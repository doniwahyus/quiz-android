package com.example.quiz.model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("data")
    private String data;

    public String getData() {
        return data;
    }
}
