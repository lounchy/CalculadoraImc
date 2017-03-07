package com.example.daini.calculadoraimc.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.daini.calculadoraimc.EscuchaButton;
import com.example.daini.calculadoraimc.R;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Log.d(getClass().getCanonicalName(), "Estoy en LoginActivity ");

        SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        boolean estaLogeado = sharedPreferences.getBoolean("usuarioLogeado", false);

        if (estaLogeado){//no esta logeado
            startActivity(new Intent(this, CalculadoraActivity.class));
        }


        EscuchaButton escuchaButton = new EscuchaButton(this);

        Button entrar = (Button)findViewById(R.id.entrar_button);
        entrar.setOnClickListener(escuchaButton);

        TextView pasarAlRegistro = (TextView)findViewById(R.id.pasar_al_registro);
        pasarAlRegistro.setOnClickListener(escuchaButton);
    }



}
