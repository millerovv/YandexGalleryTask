package com.example.alexandermelnikov.yandexgallerytask.api;

import com.example.alexandermelnikov.yandexgallerytask.model.api.ResponseRoot;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by AlexMelnikov on 17.04.18.
 */

public interface ApiService {

   @GET("search")
   Call<ResponseRoot> getImages(@Header("Authorization") String apiKey,
                                @Query("query") String phrase,
                                @Query("per_page") int numberOfResultsPerPage);
}
