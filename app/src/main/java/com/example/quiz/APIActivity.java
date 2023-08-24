package com.example.quiz;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.model.ApiResponse;
import com.example.quiz.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIActivity extends AppCompatActivity {

    private TextView responseTextView;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        responseTextView = findViewById(R.id.responseTextView);

        // Inisialisasi Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.example.com/") // Ganti dengan base URL API yang sesuai
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Buat instance dari ApiService
        apiService = retrofit.create(ApiService.class);

        // Lakukan request ke API
        fetchDataFromAPI();
    }

    private void fetchDataFromAPI() {
        // Lakukan request ke API menggunakan ApiService
        Call<ApiResponse> call = apiService.getData();

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        String responseData = apiResponse.getData();
                        responseTextView.setText(responseData);
                    }
                } else {
                    Log.e("APIActivity", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("APIActivity", "Error fetching data from API: " + t.getMessage());
            }
        });
    }
}
