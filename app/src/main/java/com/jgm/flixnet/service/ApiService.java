package com.jgm.flixnet.service;

import com.jgm.flixnet.model.Serie;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface ApiService {

    //Se definen las llamadas a la API
    @GET("api/serie")
    Call<Serie> getSerie(
            @Field("id")
            int id
    );

}
