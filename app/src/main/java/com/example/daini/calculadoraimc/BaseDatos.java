package com.example.daini.calculadoraimc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.daini.calculadoraimc.Models.Historial;
import com.example.daini.calculadoraimc.Models.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daini on 24/02/2017.
 */

public class BaseDatos extends SQLiteOpenHelper {
    private static final String SQL_CREA_TABLA_USUARIO = "CREATE TABLE USUARIO(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, altura TEXT, usuario TEXT, contrasenia TEXT)";
    private static final String SQL_CREA_TABLA_HISTORIAL = "CREATE TABLE HISTORIAL(id INTEGER PRIMARY KEY AUTOINCREMENT, usrID INTEGER, resultado TEXT, nrcalulo INTEGER)";
    public static final String BASA_DATTOS_HISTORIAL = "Historial";
    public static final String BASA_DATTOS_USUARIO = "Usuario regstro";



    public BaseDatos(Context context, String nombre_basa_datos, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, nombre_basa_datos, cursorFactory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREA_TABLA_USUARIO);
        db.execSQL(SQL_CREA_TABLA_HISTORIAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //methodo para insertar usuario registrado
    public void insertarUsuario(Usuario usuario) {
        SQLiteDatabase basaDatos = getWritableDatabase(); //consultar los datos
        basaDatos.execSQL("INSERT INTO USUARIO(nombre, altura, usuario, contrasenia)" +
                "VALUES('" +
                usuario.getAltura() + "'," +
                "'" + usuario.getAltura() + "'," +
                "'" + usuario.getUsuario() + "'," +
                "'" + usuario.getContrasenia() + "'" + ")");
        Log.e(getClass().getCanonicalName(), "Registrado con usuario : " + usuario.getUsuario() + " contraseña : " + usuario.getContrasenia());
        basaDatos.close();

    }


    //methodo para comprobar si cuenta existe con contraseña
    public boolean existeCuenta(Usuario usuario)
    {
        boolean existe = false;
        String CONSULTA_CUENTA = "SELECT * FROM USUARIO WHERE usuario = '" + usuario.getUsuario() + "'AND contrasenia = '" + usuario.getContrasenia() + "'";
        SQLiteDatabase basaDatos = getReadableDatabase();
        Cursor cursor = basaDatos.rawQuery(CONSULTA_CUENTA, null);
        if (cursor.getCount()==1){
            existe = true;
            cursor.close();
        }

        basaDatos.close();
        return existe;
    }
    //methodo para comprobar si contraseña corecta
    public boolean contraseniaCorecta(Usuario usuario)
    {
        boolean corecta = false;
        String CONSULTA_CONTRASENIA = "SELECT * FROM USUARIO WHERE contrasenia = '" + usuario.getContrasenia() + "'";
        SQLiteDatabase basaDatos = getReadableDatabase();
        Cursor cursor = basaDatos.rawQuery(CONSULTA_CONTRASENIA, null);
        if (cursor.getCount()==1){
            corecta = true;
            cursor.close();
        }

        basaDatos.close();
        return corecta;
    }
    //methodo par comprobar si nombre usuario esta libre
    public boolean existeNombreUsuario(Usuario usuario){
        boolean existe = false;
        String CONSULTA_NOMBRE_USUARIO = "SELECT * FROM USUARIO WHERE usuario = '" + usuario.getUsuario() +"'";
        SQLiteDatabase basaDatos = getReadableDatabase();
        Cursor cursor = basaDatos.rawQuery(CONSULTA_NOMBRE_USUARIO, null);
        if (cursor.getCount() == 1){//hay 1 usuario con este nombre de usuario
            existe = true;
            Log.e(getClass().getCanonicalName(), "Usurio existe " + existe);
        }else {
            existe = false;
            Log.e(getClass().getCanonicalName(), "Usurio existe " + existe);
            cursor.close();
        }

        basaDatos.close();
        return existe;

    }

        public void insertarCalculoImc(Historial historial){
        SQLiteDatabase basaDatos = getWritableDatabase(); //consultar los datos
        basaDatos.execSQL("INSERT INTO HISTORIAL(usrID, resultado, nrcalulo)" +
                "VALUES('" +
                historial.getUsrID() + "'," +
                "'" + historial.getNrCalculo() + "'," +
                "'" + historial.getResultadoImc() + "'" +")");
        Log.d(getClass().getCanonicalName(), "IMC : " + historial.getResultadoImc() + " calculo Nr : " + historial.getNrCalculo() + " usrID : " + historial.getUsrID());
        basaDatos.close();
    }

    public int sacarId(Usuario usuario){
        int id = 0;
        String CONSULTA = "SELECT * FROM USUARIO WHERE usuario = '" + usuario.getUsuario() + "'AND contrasenia = '" + usuario.getContrasenia() + "'";
        SQLiteDatabase baseDatos = this.getReadableDatabase();

        Cursor cursor = baseDatos.rawQuery(CONSULTA, null);

        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            do {
                id = cursor.getInt(0);
            }while (cursor.moveToNext());
            cursor.close();
        }
        baseDatos.close();
        return id;

    }

    //Aqui necesito ayuda
    public List<Historial> sacarHistorialCalculos(int id){
        List<Historial> arrayList = null;

        String CONSULTA = "SELECT resultado, nrcalulo FROM HISTORIAL WHERE usrID = " + id;
        SQLiteDatabase basaDatos = getReadableDatabase();
        Cursor cursor = basaDatos.rawQuery(CONSULTA, null);


        if (cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            arrayList = new ArrayList<>();
            do {
                 int calculoNr = cursor.getInt(0);
                 String resultado = cursor.getString(1);


                Historial historial = new Historial(id, calculoNr, resultado);
                arrayList.add(historial);
            }while (cursor.moveToNext());
            cursor.close();
        }
        basaDatos.close();

        return arrayList;
    }

//    public String sacarAltura(){
//        String alturas = "";
//        String CONSULTA_ALTURA = "SELECT altura FROM USUARIO";
//        SQLiteDatabase basaDatos = getReadableDatabase();
//        Cursor cursor = basaDatos.rawQuery(CONSULTA_ALTURA, null);
//
//        if (cursor != null && cursor.getCount()>0){
//            cursor.moveToFirst();
//
//            do {
//                alturas = cursor.getString(0);
//
//            }while (cursor.moveToNext());
//            cursor.close();
//        }
//        basaDatos.close();
//        return alturas;
//    }

}
