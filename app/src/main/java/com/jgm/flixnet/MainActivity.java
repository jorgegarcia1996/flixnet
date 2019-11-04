package com.jgm.flixnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.jgm.flixnet.adapter.SeriesAdapter;
import com.jgm.flixnet.api.ApiClient;
import com.jgm.flixnet.model.Serie;
import com.jgm.flixnet.model.Usuario;
import com.jgm.flixnet.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String URL_BASE_FLIXNET = "https://ajsb.000webhostapp.com/public/";

    private RecyclerView recycler;
    private FirebaseAuth fbAuth;

    private ApiService service;
    private List<Serie> listaSeries;
    private SeriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbAuth = FirebaseAuth.getInstance();

        //Creamos el adaptador para la lista de series
        listaSeries = new ArrayList<Serie>();
        adapter = new SeriesAdapter(this, new SeriesAdapter.SeriesListenerInterface() {
            @Override
            public void onSerieClickListener(Serie item) {
                Toast.makeText(getApplicationContext(), "Se ha pulsado en" + item.getTitulo(), Toast.LENGTH_LONG).show();

            }
        } /*new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Se ha pulsado en un elemento", Toast.LENGTH_LONG);
            }
        }*/);

        //Instanciamos el adaptador
        recycler = findViewById(R.id.mainListShows);

        //Definimos el layout del recycler
        recycler.setLayoutManager(new LinearLayoutManager(this));

        //especifocamos el adaptador para el recycler
        recycler.setAdapter(adapter);

        //Instancia del cliente de la API
        service = ApiClient.getService(URL_BASE_FLIXNET);
        registerForContextMenu(recycler);

        //Método definido más abajo
        loadShows();


        //Recopilar la información de la intención
        Bundle bundle = getIntent().getExtras();

        //Recuperar el objeto del bundle
        Usuario usuario = (Usuario) bundle.getSerializable("usuario");


        //Recuperar los datos cuando se pasan individualmente
        //String nombre = bundle.getString("nombre");
        //String apellidos = bundle.getString("apellidos");

    }

    //Menú principal
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ppal, menu);
        return true;
    }

    //Opciones del menú principal
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

    //Menú contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_ppal_context, menu);
    }

    //Opciones del menú contextual
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Serie serie;

        switch(item.getItemId()) {
            case R.id.ctxAdd:
                serie = listaSeries.get(adapter.getIndex());
                Toast.makeText(this, "Añadido a favoritos: " + serie.getTitulo(), Toast.LENGTH_LONG).show();
                break;
            case R.id.ctxDel:
                serie = listaSeries.get(adapter.getIndex());
                Toast.makeText(this, "Quitado de favoritos: " + serie.getTitulo(), Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);

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
                    Log.i("flixnet", "Las series se han cargado.");

                    //Notificar los cambios en los datos
                    adapter.setData(listaSeries);
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
