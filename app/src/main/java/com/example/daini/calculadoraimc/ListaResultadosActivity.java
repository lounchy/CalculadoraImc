package com.example.daini.calculadoraimc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ListaResultadosActivity extends AppCompatActivity {
    private String resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_resultados);
        //Declaro basa de datos
        BasaDatos basaDatos = new BasaDatos(this, BasaDatos.NOMBRE_TABLA, null, 1);
        //Declaro calculadora
        Calculadora calculadora = new Calculadora(resultados);
        //Saco resultado guardado
        resultados = basaDatos.sacarResultado(calculadora);

        //defino textview
        TextView tv_res = (TextView)findViewById(R.id.lista_resultados);
        //pinto resultado sacado
        tv_res.setText(resultados);

    }
}
