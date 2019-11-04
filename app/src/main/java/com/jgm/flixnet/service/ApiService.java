package com.jgm.flixnet.service;

import com.jgm.flixnet.model.Capitulo;
import com.jgm.flixnet.model.Serie;
import com.jgm.flixnet.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.GET;

public interface ApiService {

    //Se definen las llamadas a la API

    //Obtener una sola serie
    @GET("api/serie")
    Call<Serie> getSerie(
            @Query("id") int id
    );

    //Obtener todas las series
    @GET("api/series")
    Call<List<Serie>> getSeries();



    //Obtener un solo capitulo
    @GET("api/capitulo")
    Call<Capitulo> getCapitulo(
            @Query("id") int id
    );

    //Login en la API
    @POST("api/login")
    @FormUrlEncoded
    Call<Usuario> doLogin(
            @Header("Authorization") String token, //Token de usuario
            @Field("email") String email,
            @Field("password") String pass
    );

    //Registro en la API
    /*@POST("api/register")
    @FormUrlEncoded
    @Multipart
    @Headers("Content-Type:application/json")
    Call<Usuario> doRegister(
            @Header("Authorization") String token, //Token de usuario
            @Field("email") String email,
            @Field("password") String pass
            //Resto de campos
    );*/
}
