package com.jgm.flixnet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
}
