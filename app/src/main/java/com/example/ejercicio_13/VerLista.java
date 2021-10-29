package com.example.ejercicio_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ejercicio_13.Configuracion.Conexion;
import com.example.ejercicio_13.Configuracion.Transacciones;
import com.example.ejercicio_13.Personas.Persona;

import java.util.ArrayList;

public class VerLista extends AppCompatActivity {
    Conexion conexion;
    ListView listapersonas;
    ArrayList<Persona> lista;
    ArrayList<String> ArregloPersonas;
    Button agrego,actualizo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista);

        conexion=new Conexion(this, Transacciones.NameDatabase, null, 1);
        listapersonas=(ListView) findViewById(R.id.listapersonas);

        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter( this, android.R.layout.simple_list_item_1, ArregloPersonas);
        listapersonas.setAdapter(adp);
    agrego=(Button) findViewById(R.id.btnagregarpersona2);
    actualizo=(Button) findViewById(R.id.btnactualizar2);

    agrego.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Ingresar.class);
            startActivity(intent);
        }
    });
    actualizo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Actualizar.class);
            startActivity(intent);
        }

    });
    }
    private void ObtenerListaPersonas()
    {
        SQLiteDatabase db= conexion.getReadableDatabase();
        Persona list_personas = null;
        lista = new ArrayList<Persona>();

        //cursor de base de dats : nos apoya a recorrer la informacion de la tabla a la cual consultamos//

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablaPersonas, null);

        //Recorremos la informacion del cursor

        while(cursor.moveToNext())
        {
            list_personas=new Persona();
            list_personas.setId(cursor.getInt(0));
            list_personas.setNombres(cursor.getString(1));
            list_personas.setApellidos(cursor.getString(2));
            list_personas.setEdad(cursor.getInt(3));
            list_personas.setCorreo(cursor.getString(4));
            list_personas.setDirrecion(cursor.getString(5));
            lista.add(list_personas);
        }
        cursor.close();

        filllist();

    }

    private void filllist()
    {
        ArregloPersonas=new ArrayList<String>();

        for (int i=0; i< lista.size(); i++)
        {
            ArregloPersonas.add(lista.get(i).getId() + " | "
                    +lista.get(i).getNombres()+" | "
                    +lista.get(i).getApellidos()+" |" + lista.get(i).getEdad() + " | "
                    +lista.get(i).getCorreo()+" | "
                    +lista.get(i).getDirrecion());
        }
    }
}