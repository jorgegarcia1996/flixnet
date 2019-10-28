package com.jgm.flixnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.jgm.flixnet.model.Usuario;
import com.jgm.flixnet.service.ApiService;

public class MainActivity extends AppCompatActivity {

    private final String URL_API_FLIXNET = "https://ajsb.000webhostapp.com/public/";

    private TextView text;
    private FirebaseAuth fbAuth;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbAuth = FirebaseAuth.getInstance();

        //Instancia del cliente de la API
        apiService = ApiClient.getClient(URL_API_FLIXNET);


        //Recopilar la información de la intención
        Bundle bundle = getIntent().getExtras();

        //Recuperar el objeto del bundle
        Usuario usuario = (Usuario) bundle.getSerializable("usuario");


        //Recuperar los datos cuando se pasan individualmente
        //String nombre = bundle.getString("nombre");
        //String apellidos = bundle.getString("apellidos");

        text = findViewById(R.id.textoMain);
        text.setText("Hola " + usuario.getNombre() + " " + usuario.getApellidos() + ".");

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
}
