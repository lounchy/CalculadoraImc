package com.example.daini.calculadoraimc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.widget.Button;

public class CalculadoraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        //Seteo escucha button
        EscuchaButton escuchaButton = new EscuchaButton(this);
        //Seteo botton calcular
        Button calcular = (Button)findViewById(R.id.calcular_button);
        calcular.setOnClickListener(escuchaButton);
        //Seteo botton guardar y ver
        Button guardar = (Button)findViewById(R.id.guardar_resultado_button);
        guardar.setOnClickListener(escuchaButton);

    }
}
