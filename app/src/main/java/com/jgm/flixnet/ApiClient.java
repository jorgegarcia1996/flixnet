package com.jgm.flixnet;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jgm.flixnet.service.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ApiClient {

    private static OkHttpClient httpClient;
    private static Gson gson;
    private static Retrofit retrofit;

    private ApiClient() {

    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //Configurar el cliente de retrofit con la interfaz de ApiService
    public static ApiService getClient(String url) {
        //Construir el cliente HTTP
        httpClient = new OkHttpClient.Builder().build();

        //Instanciamos el parser de JSON (GSON)
        gson = new GsonBuilder().setLenient().create();

        //Construir el cliente de Retrofit
        retrofit = new Retrofit.Builder()
                   .baseUrl(url)
                   .client(httpClient)
                   .build();

        //devolvemos una instancia del servicio
        return retrofit.create(ApiService.class);
    }

}
