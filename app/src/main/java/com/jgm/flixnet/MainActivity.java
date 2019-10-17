package com.jgm.flixnet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public final int REGISTER_CODE = 666;

    private Button btnLogin, btnRegister;
    private FirebaseAuth fbAuth;
    private FirebaseDatabase fbDatabase;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //instancia de firebaseAuth y firebaseDatabase
        fbAuth = FirebaseAuth.getInstance();


        //Enlazar los botones
        btnLogin = findViewById(R.id.loginButton);
        btnRegister = findViewById(R.id.registerButton);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);

        //Listener para el login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Conseguir el email y la contraseña
                String ema = email.getText().toString();
                String pass = password.getText().toString();

                //Loguearse en firebase y obtener datos de la fbDatabase
                fbAuth.signInWithEmailAndPassword(ema, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    FirebaseUser user = fbAuth.getCurrentUser();
                                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                                } else {
                                    //UID del usuario actual
                                    String uid = fbAuth.getCurrentUser().getUid();

                                    //Instancia de la Base de datos
                                    fbDatabase = FirebaseDatabase.getInstance();

                                    //Referencia al usuario de la base de datos
                                    DatabaseReference userRef = fbDatabase.getReference().child("usuarios/" + uid);

                                    //Crear un escuchador
                                    userRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            //Comprobar que existe el hijo que busco
                                            if (dataSnapshot.hasChildren()) {
                                                //String user = dataSnapshot.child("nombre").getValue().toString();
                                                //user += dataSnapshot.child("apellidos").getValue().toString();

                                                Usuario usuario = dataSnapshot.getValue(Usuario.class);

                                                Toast.makeText(getApplicationContext(), "COMPLETADO EL LOGIN - " + usuario, Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }
                            }
                        }); //Fin del login



                //Noificación con Toast, Inflador, editar texto, y mostrar notificacion
               /* LayoutInflater inflater = getLayoutInflater();
                View vista = inflater.inflate(R.layout.toast_layout, null);

                TextView text = vista.findViewById(R.id.toastText);
                text.setText("Haciendo login...");

                Toast toast;
                toast = new Toast(getApplicationContext());
                toast.setView(vista);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, -450);
                toast.show();*/

                //Forma sencillla
                //Toast.makeText(getApplicationContext(), "Haciendo login", Toast.LENGTH_LONG).show();

                //Crear el snackbar
                //Snackbar snack = Snackbar.make(v, "Logeandose...", BaseTransientBottomBar.LENGTH_INDEFINITE);

                //Definir un listener sobre el snackbar, no es necesario rellenar el listener
                //Forma de acceder a un string del archivo strings.xml
                //snack.setAction(getResources().getText(R.string.lab_ok), new View.OnClickListener() {
                //    @Override
                //    public void onClick(View v) {
                //    }
                //});

                //Modificar colores de la notificación
                //snack.setTextColor(getResources().getColor(R.color.colorPrimary));
                //snack.setBackgroundTint(getResources().getColor(R.color.colorAccent));

                //Mostrar el nackbar
                //snack.show();

            }
        });

        //Listener del botón de registro
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Crear intención y ejecutarla para cambiar de actividad
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                //para comenzar una actividad se necesita una intención y un código
                startActivityForResult(intent, REGISTER_CODE);

                //Empezar una actividad a pelo
                //startActivity(intent);

            }
        });
    }

    //Método para recopilar el resultado de una actividad
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Comprobar que la actividad deseada ha terminado de la manera deseada
        if (requestCode == REGISTER_CODE) { //Codigo de la actividad deseada
            if (resultCode == RESULT_OK) { //Código de finalización deseado
                //Cógigo que queramos ejecutar al cumplir las 2 condiciones

                //Para utilizar el Snackbar sirve pasar una vista cualquiera del layout
                //Forma corta
                Snackbar.make(btnLogin,"La actividad de registro se ha Completado", BaseTransientBottomBar.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Snackbar.make(btnLogin,"La actividad de registro se ha Cancelado", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        }
    }
}
