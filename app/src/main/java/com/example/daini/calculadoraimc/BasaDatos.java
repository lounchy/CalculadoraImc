package com.example.daini.calculadoraimc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Daini on 20/02/2017.
 */

public class BasaDatos extends SQLiteOpenHelper {

    //seteo tabla
    private static final String SQL_CREA_TABLA_HISTORIAL =
            "CREATE TABLE HISTORIAL(id INTEGER PRIMARY KEY AUTOINCREMENT, resultado TEXT)";
    //seteo nombre de tabla
    public static final String NOMBRE_TABLA = "HISTORIAL";

    //constructor
    public BasaDatos(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, nombre, factory, version);
    }

    //Methodo obligotario
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREA_TABLA_HISTORIAL);

    }
    //Methodo obligotario
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //va insertar el resultado
    public void insertarResultados(Calculadora resultados)
    {
        SQLiteDatabase basaDatos = getWritableDatabase();
        basaDatos.execSQL("INSERT INTO HISTORIAL(resultado) VALUES ('" + resultados.getResultado()+"') ");
        basaDatos.close();
    }
    //va a sacar resultado
    public String sacarResultado(Calculadora resultados){
        String res = "";
        String CONSULTA_RESULTADO = "SELECT * FROM HISTORIAL WHERE resultado = '" + resultados.getResultado()+"'";
        SQLiteDatabase basaDatos = getReadableDatabase();
        Cursor cursor = basaDatos.rawQuery(CONSULTA_RESULTADO, null);

        if (cursor.getCount()==1){
            res = resultados.getResultado();
        }
        basaDatos.close();
        cursor.close();
        return res;
    }
}
