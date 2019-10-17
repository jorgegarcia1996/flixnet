package com.jgm.flixnet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    private Button btnReg;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        btnReg = findViewById(R.id.confirmRegister);
        btnCancel = findViewById(R.id.cancelRegister);

        //Listener para el botón de confirmar registro
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proceso del registro

                setResult(RESULT_OK);
                finish();

                //Al finalizar una actividad es obligatorio finalizar con un result tras el finish()
                return;
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


}
