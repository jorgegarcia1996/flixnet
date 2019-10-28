package com.jgm.flixnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.jgm.flixnet.adapter.SeriesAdapter;
import com.jgm.flixnet.api.ApiClient;
import com.jgm.flixnet.model.Serie;
import com.jgm.flixnet.model.Usuario;
import com.jgm.flixnet.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String URL_BASE_FLIXNET = "https://ajsb.000webhostapp.com/public/";

    private RecyclerView recycler;
    private FirebaseAuth fbAuth;

    private ApiService service;
    private List<Serie> listaSeries = null;
    private SeriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbAuth = FirebaseAuth.getInstance();

        recycler = findViewById(R.id.mainListShows);

        //Instancia del cliente de la API
        service = ApiClient.getService(URL_BASE_FLIXNET);

        loadShows();



        //Recopilar la información de la intención
        Bundle bundle = getIntent().getExtras();

        //Recuperar el objeto del bundle
        Usuario usuario = (Usuario) bundle.getSerializable("usuario");


        //Recuperar los datos cuando se pasan individualmente
        //String nombre = bundle.getString("nombre");
        //String apellidos = bundle.getString("apellidos");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ppal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_logout:
                //Hacer el logout en firebase y la API
                fbAuth.signOut();

                //Volver a la pantalla de login
                setResult(RESULT_OK);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadShows() {
        //#############################################################################################

        //Una forma de acceder a los datos de la api de forma asíncrona
        service.getSeries().enqueue(new Callback<List<Serie>>() {
            @Override
            public void onResponse(Call<List<Serie>> call, Response<List<Serie>> response) {
                if (response.isSuccessful()) {
                    listaSeries = response.body();
                    //Proceso para mostrar los datos
                    adapter = new SeriesAdapter(listaSeries);


                    Log.i("flixnet", "Las series se han cargado.");
                }
            }

            @Override
            public void onFailure(Call<List<Serie>> call, Throwable t) {
                Log.i("flixnet", "FALLO");
            }
        });


        //#############################################################################################
    }
}
