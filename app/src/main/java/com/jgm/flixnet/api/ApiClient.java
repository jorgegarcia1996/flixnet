package com.jgm.flixnet.api;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jgm.flixnet.service.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static OkHttpClient httpClient;
    private static Gson gson;
    private static Retrofit retrofit;

    private static ApiService service = null;

    private ApiClient() {

    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //Configurar el cliente de retrofit con la interfaz de ApiService
    public static ApiService getService(String url) {
        if (service == null) {
            //Construir el cliente HTTP
            httpClient = new OkHttpClient.Builder().build();

            //Instanciamos el parser de JSON (GSON)
            gson = new GsonBuilder().setLenient().create();

            //Construir el cliente de Retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            //devolvemos una instancia del servicio
            service = retrofit.create(ApiService.class);
        }
        return service;
    }

}
