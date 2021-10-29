package com.example.ejercicio_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio_13.Configuracion.Conexion;
import com.example.ejercicio_13.Configuracion.Transacciones;

public class Ingresar extends AppCompatActivity {
Button agregar,limpieza,mostrarlist;
EditText nombres,apellidos,edades,correos,direcciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
        agregar=(Button) findViewById(R.id.btnagregarpersona);
        limpieza=(Button)findViewById(R.id.btnlimpiar);
        mostrarlist=(Button) findViewById(R.id.btnmostrarlista);
        nombres=(EditText) findViewById(R.id.txtnombre);
        apellidos=(EditText) findViewById(R.id.txtapellido);
        edades=(EditText) findViewById(R.id.txtedad);
        correos=(EditText) findViewById(R.id.txtcorreo);
        direcciones=(EditText) findViewById(R.id.txtdireccion);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if(!nombres.getText().toString().isEmpty() && !direcciones.getText().toString().isEmpty() && !apellidos.getText().toString().isEmpty() && !edades.getText().toString().isEmpty() && !correos.getText().toString().isEmpty()){
agregarPersonas();
}
else{
    Toast.makeText(getApplicationContext(),"Por favor, llenar los espacios vacios" ,Toast.LENGTH_LONG).show();
}
            }
        });
        limpieza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });
        mostrarlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VerLista.class);
                startActivity(intent);
            }
        });

    }
    void limpiar()
    {
        nombres.setText("");
        apellidos.setText("");
        edades.setText("");
        correos.setText("");
        direcciones.setText("");
    }
    private void agregarPersonas(){
        Conexion conexion=new Conexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db=conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edades.getText().toString());
        valores.put(Transacciones.correo, correos.getText().toString());
        valores.put(Transacciones.direccion, direcciones.getText().toString());

        Long resultado = db.insert(Transacciones.tablaPersonas,Transacciones.id, valores);
        Toast.makeText(getApplicationContext(),"Registro Ingresado : Codigo : " + resultado.toString(),Toast.LENGTH_LONG).show();
        db.close();
        limpiar();
    }
}