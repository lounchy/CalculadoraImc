package com.example.daini.calculadoraimc.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.daini.calculadoraimc.EscuchaButton;
import com.example.daini.calculadoraimc.R;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Log.d(getClass().getCanonicalName(), "Estoy en RegistroActivity");
        getSupportActionBar().hide();

        //Seteo escucha button
        EscuchaButton escuchaButton = new EscuchaButton(this);

        //Seteo boton registrar usuario
        Button registrar = (Button)findViewById(R.id.registrar_usuario_buton);
        registrar.setOnClickListener(escuchaButton);
    }
}
