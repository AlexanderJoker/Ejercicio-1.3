package com.example.ejercicio_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button agregar,actualizar,ver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregar=(Button)findViewById(R.id.btnagregar);
        actualizar=(Button)findViewById(R.id.btnactualizar);
        ver=(Button) findViewById(R.id.btnmostrar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Ingresar.class);
                startActivity(intent);
            }
         });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Actualizar.class);
                startActivity(intent);
            }
         });

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VerLista.class);
                startActivity(intent);
            }
         });

    }
}