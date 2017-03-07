package com.example.daini.calculadoraimc.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daini.calculadoraimc.EscuchaButton;
import com.example.daini.calculadoraimc.Models.Calucladora;
import com.example.daini.calculadoraimc.Models.Historial;
import com.example.daini.calculadoraimc.R;

import java.util.ArrayList;

public class ResultadosActivity extends AppCompatActivity {
    private float resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        Log.d(getClass().getCanonicalName(), "Estoy en ResultadosActivity");
        EscuchaButton escuchaButton = new EscuchaButton(this);

        Button consultar = (Button) findViewById(R.id.consultar_rangos_button);
        consultar.setOnClickListener(escuchaButton);
        SharedPreferences sharedPreferences = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        int usrID = sharedPreferences.getInt("usrID", 0);


        ArrayList<Calucladora> arrayList = new ArrayList<>();
        arrayList.add(new Calucladora(19, "Bajo Peso", R.drawable.image_imc_bajo_peso));
        arrayList.add(new Calucladora(25, "Peso Ideal", R.drawable.image_imc_peso_ideal));
        arrayList.add(new Calucladora(30, "Sobrepeso", R.drawable.image_imc_sobrepeso));
        arrayList.add(new Calucladora(40, "Obesidad", R.drawable.image_imc_obesidad));
        arrayList.add(new Calucladora(99, "Obesidad Morbida", R.drawable.image_imc_obesidad_morbida));

        TextView clasificacion = (TextView)findViewById(R.id.clasificacion_imc_tv);
        TextView valor = (TextView)findViewById(R.id.valor_imc_tv);
        ImageView imagen = (ImageView)findViewById(R.id.imagen_imc);

        Intent intent = getIntent();
        resultado = intent.getFloatExtra("Resultado", 0);
        

        for (Calucladora indice : arrayList){
            if (resultado<indice.getLimiteImc()){
                clasificacion.setText(indice.getClasificacionImc());
                valor.setText(String.format("%.1f",resultado));
                imagen.setImageResource(indice.getResultadoImagen());
                break;
            }
        }

    }

}
