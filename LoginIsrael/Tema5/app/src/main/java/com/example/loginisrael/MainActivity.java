package com.example.loginisrael;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.materialswitch.MaterialSwitch;

public class MainActivity extends AppCompatActivity {

    private static final String CORREO_VALIDO = "israel@correo.com";
    private static final String CLAVE_VALIDA = "1234";
    private int intentos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etCorreo = findViewById(R.id.correoponer);
        EditText etClave = findViewById(R.id.contraponer);
        View btnContinuar = findViewById(R.id.continuar);
        TextView vacio = findViewById(R.id.vacio);
        MaterialSwitch swRecordar = findViewById(R.id.recordar);

        btnContinuar.setOnClickListener(v -> {
            String correo = etCorreo.getText().toString().trim();
            String clave = etClave.getText().toString();
            boolean recordar = swRecordar.isChecked();

            if (recordar == true && correo.equals(CORREO_VALIDO) && clave.equals(CLAVE_VALIDA)) {
                //Recorda contraseña activado
                vacio.setText(R.string.Falta_recordar);
                vacio.setTextColor(ContextCompat.getColor(this, R.color.verde));
            } else if (correo.equals(CORREO_VALIDO) && clave.equals(CLAVE_VALIDA)) {
                // correcto, en verde (¿y si swRecordar está activado?)
                vacio.setText(R.string.acceso_correcto);
                vacio.setTextColor(ContextCompat.getColor(this, R.color.correcto));
            }else {
                // incorrecto, en rojo
                vacio.setText(R.string.acceso_incorrecto);
                vacio.setTextColor(ContextCompat.getColor(this, R.color.incorrecto));
            }
        });


    }
}