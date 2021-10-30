package com.example.ejercicio_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio_13.Configuracion.Conexion;
import com.example.ejercicio_13.Configuracion.Transacciones;

public class Actualizar extends AppCompatActivity {
    Conexion conexion;
    EditText id, nombres, apellidos, edad, correo, direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        conexion = new Conexion(this, Transacciones.NameDatabase, null, 1);

        //Botones
        Button consulta = (Button) findViewById(R.id.btnbuscar);
        Button eliminar = (Button) findViewById(R.id.btneliminar);
        Button actualizar = (Button) findViewById(R.id.btnactualizacon);

        id = (EditText) findViewById(R.id.txtids);
        nombres = (EditText) findViewById(R.id.txtnombres);
        apellidos = (EditText) findViewById(R.id.txtapellidos);
        edad = (EditText) findViewById(R.id.txtedades);
        correo = (EditText) findViewById(R.id.txtcorreoselec);
        direccion = (EditText) findViewById(R.id.txtdirecciones);

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nombres.getText().toString().isEmpty() && !apellidos.getText().toString().isEmpty() && !edad.getText().toString().isEmpty() && !correo.getText().toString().isEmpty() && !direccion.getText().toString().isEmpty()){
                    Actualizar();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Por favor, llenar los espacios vacios" ,Toast.LENGTH_LONG).show();

                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });
    }


    private void Buscar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        //Parametros de configuracion de la sentencia SELECT
        String[] params = {id.getText().toString()}; //parametro de la busqueda
        String[] fields = {Transacciones.nombres,
                Transacciones.apellidos,
                Transacciones.correo,
                Transacciones.edad,Transacciones.direccion};
        String wherecond = Transacciones.id + "=?";

        try {
            Cursor cdata = db.query(Transacciones.tablaPersonas, fields, wherecond, params, null, null, null);
            cdata.moveToFirst();
            nombres.setText(cdata.getString(0));
            apellidos.setText(cdata.getString(1));
            correo.setText(cdata.getString(2));
            edad.setText(cdata.getString(3));
            direccion.setText(cdata.getString(4));

            Toast.makeText(getApplicationContext(), "Consultado con exito", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            limpiar();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado", Toast.LENGTH_LONG).show();
        }
    }

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        String[] params = {id.getText().toString()};
        String wherecond = Transacciones.id + "=?";
        db.delete(Transacciones.tablaPersonas, wherecond, params);
        Toast.makeText(getApplicationContext(), "Dato eliminado", Toast.LENGTH_LONG).show();
        limpiar();
    }

    private void Actualizar() {

        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] params = {id.getText().toString()};
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion,direccion.getText().toString());
        db.update(Transacciones.tablaPersonas, valores, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato actualizado", Toast.LENGTH_LONG).show();
        limpiar();
    }

    void limpiar(){
    id.setText("");
    nombres.setText("");
    edad.setText("");
    apellidos.setText("");
    correo.setText("");
    direccion.setText("");
    }
}