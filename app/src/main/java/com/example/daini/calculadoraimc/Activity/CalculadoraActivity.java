package com.example.daini.calculadoraimc.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.daini.calculadoraimc.BaseDatos;
import com.example.daini.calculadoraimc.EscuchaButton;
import com.example.daini.calculadoraimc.Models.Historial;
import com.example.daini.calculadoraimc.Models.Usuario;
import com.example.daini.calculadoraimc.R;

public class CalculadoraActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        Log.d(getClass().getCanonicalName(), "Estoy en CalculadoraActivity ");
        escucharBotones();//escucha todos botones relacionados con esta clase
        snackBar();//Saludar usuario
//      autoAltura();//como altura no cambie saca altura de basa de datos y pone en edittext
        //Añado valores a Basa de datos HISTORIAL
        BaseDatos baseDatos = new BaseDatos(this, BaseDatos.BASA_DATTOS_HISTORIAL, null, 1);
        SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
        String usuario = sharedPreferences.getString("usuario", "");
        String pw = sharedPreferences.getString("pw","");
        int usrID = sharedPreferences.getInt("usrID", 0);



        Log.e(getClass().getCanonicalName(), "Estoy en CalculadoraActivity  con id " + usrID + usuario + pw);

//        editor.commit();




    }

    //Creo Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, 1, "Abrir Web");
        menu.add(Menu.NONE, 10, 1, "Salir");
        return true;
    }

    //Seteo menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                startActivity(new Intent(this, WebViewActivity.class));//lanzo web activity
                break;
            case 10:
                casoSalir();//saldra de applicasion
                break;
        }
        return false;
    }

    //Methodo que encarga de todoas botones
    private void escucharBotones() {
        //Seteo escucha button
        EscuchaButton escuchaButton = new EscuchaButton(this);

        //Seteo botton calcular
        Button calcular = (Button) findViewById(R.id.calcular_button);
        calcular.setOnClickListener(escuchaButton);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.floating_actoin_buton);
        fab.setOnClickListener(escuchaButton);
    }
        //Methodo que saca altura de Base de datos y pasa a EditText
//    private void autoAltura() {
//
//
//        SharedPreferences sharedPreferences = getSharedPreferences("pref_Login", Context.MODE_PRIVATE);
//        String usuarioAltura = sharedPreferences.getString("usuarioAltura", "");
//
//        basaDatos = new BaseDatos(this, BaseDatos.BASA_DATTOS_USUARIO, null, 1);
//
//        altura = basaDatos.sacarAltura();
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("usuarioAltura",altura);
//        editor.commit();
//
//        EditText editText = (EditText) findViewById(R.id.introducir_altura_et);
//        editText.setText(altura);
//
//    }

    //Caso si en menu usuario toca salir
    private void casoSalir() {
        //Preparo alert dialog
        final AlertDialog alertDialog = new AlertDialog.Builder(CalculadoraActivity.this).create();
        alertDialog.setTitle("Salir");
        alertDialog.setMessage("¿Desaea salir?");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SharedPreferences sharedPreferences = CalculadoraActivity.this.getSharedPreferences("pref_Login", Context.MODE_PRIVATE);
                sharedPreferences.getBoolean("usuarioLogeado", false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("usuarioLogeado", false);//guardo que usuario no esta logeado
                editor.commit();
                CalculadoraActivity.this.finish();
            }
        });
        alertDialog.show();
    }

    //Encarga de saludar usuario
    private void snackBar()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String usuario = sharedPreferences.getString("usuario", "");
        Snackbar snackbar =Snackbar.make(findViewById(R.id.activity_calculadora), "Hola " + usuario + "!", Snackbar.LENGTH_LONG);

        //Personalizar snack bar texto
        View view = snackbar.getView();
        TextView tv = (TextView)view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.GREEN);
        tv.setTextAlignment(view.TEXT_ALIGNMENT_CENTER);
        tv.setTextSize(18);
        snackbar.show();
    }


}
