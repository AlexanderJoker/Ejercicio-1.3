package com.example.ejercicio_13.Configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion extends SQLiteOpenHelper {
    //contexto-nombre de DB-CURSOR-Version
    public Conexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //crear la tabla Photograh
        sqLiteDatabase.execSQL(Transacciones.CreateTablePersonas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldver, int newver) {
        //Eliminar las tablas al eliminar la info de DB
        sqLiteDatabase.execSQL(Transacciones.DROPTABLEPersonas);
        onCreate(sqLiteDatabase);
    }
}
