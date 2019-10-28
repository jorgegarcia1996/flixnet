package com.jgm.flixnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jgm.flixnet.model.Usuario;


public class RegisterActivity extends AppCompatActivity {

    //Utilizar la librería butterknife para ligar las vistas
    public Button btnReg;
    public Button btnCancel;
    public EditText nombre;
    public EditText apellidos;
    public EditText email;
    public EditText pass;
    public EditText confPass;
    public Spinner nacionalidad;


    private FirebaseAuth fbAuth;
    private FirebaseDatabase fbDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        //Ligar los campos a la vista
        btnReg = findViewById(R.id.confirmRegister);
        btnCancel = findViewById(R.id.cancelRegister);
        nombre = findViewById(R.id.nameRegister);
        apellidos = findViewById(R.id.lastNameRegister);
        email = findViewById(R.id.usernameRegister);
        pass = findViewById(R.id.password);
        confPass = findViewById(R.id.confirmPassword);
        nacionalidad = findViewById(R.id.nacionalidad);

        //Listener para el spinner
        /*nacionalidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        //Agregar elementos al spinner de forma programática, para ello se debe eliminar la propiedad correspondiente el layout
        /*List<String> paises = new ArrayList<String>(){{
            add("España");
            add("Francia");
            add("Japón");
            add("Alemania");
            add("Italia");
        }};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, paises);
        nacionalidad.setAdapter(adaptador);*/


        //Listener para el botón de confirmar registro
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proceso del registro
                final String ema = getField(email);
                final String nom = getField(nombre);
                final String ape = getField(apellidos);
                String pas = getField(pass);
                String con = getField(confPass);
                final String pais = nacionalidad.getSelectedItem().toString();

                //Comprobaciones
                if (ema.isEmpty() || nom.isEmpty() || ape.isEmpty() || pas.isEmpty() || con.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Campos vacíos", Toast.LENGTH_LONG).show();
                    return;
                }


                if (!pas.equals(con)) {
                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                   return;
                }

                //Registrar al usuario en el sistema de autenticación
                fbAuth = FirebaseAuth.getInstance();
                fbDatabase = FirebaseDatabase.getInstance();

                fbAuth.createUserWithEmailAndPassword(ema, pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            //Registro exitoso (hay que desloguear al usuario porque FB lo loguea automáticamente)
                            //Obtener el UID
                            String uid = fbAuth.getUid();

                            //Guardar los datos del usuario
                            Usuario usuario = new Usuario(ema, nom, ape, pais);

                            //Añadir el usuario a la base de datos
                            DatabaseReference dbRef = fbDatabase.getReference("usuarios");
                            dbRef.child(uid).setValue(usuario);

                            //Logout
                            fbAuth.signOut();
                            setResult(RESULT_OK);
                            finish();

                            //Al finalizar una actividad es obligatorio finalizar con un result tras el finish()
                            return;

                        } else {
                            Toast.makeText(getApplicationContext(), "Ha habido un error el le registro.", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
                return;
            }
        });

    }

    private String getField(EditText editText) {
        return editText.getText().toString().trim();
    }


}
