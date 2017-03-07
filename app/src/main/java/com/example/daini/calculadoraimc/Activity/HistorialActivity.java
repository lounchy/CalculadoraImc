package com.example.daini.calculadoraimc.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.daini.calculadoraimc.Adapter.HistorialAdapter;
import com.example.daini.calculadoraimc.BaseDatos;
import com.example.daini.calculadoraimc.Models.Historial;
import com.example.daini.calculadoraimc.Ordenar.OrdenarCalculoUltimo;
import com.example.daini.calculadoraimc.Ordenar.OrdenarCalculoPrimero;
import com.example.daini.calculadoraimc.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HistorialActivity extends AppCompatActivity {
    private List<Historial> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        Log.d(getClass().getCanonicalName(), "Estoy en HistorialActivity ");




        BaseDatos baseDatos = new BaseDatos(this, BaseDatos.BASA_DATTOS_HISTORIAL, null, 1);
        SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        int usrID = sharedPreferences.getInt("usrID", 0);
        int calculoNr = sharedPreferences.getInt("calculoNr", 0);

            arrayList = baseDatos.sacarHistorialCalculos(usrID);




        ListView listView = (ListView)findViewById(R.id.historial_list_view);
        if (arrayList != null) {

            HistorialAdapter historialAdapter = new HistorialAdapter(this, arrayList);


            listView.setAdapter(historialAdapter);
            ordenarPor();
        }
        else{
            TextView textView = (TextView)findViewById(R.id.historial_ordenar_tv);
            textView.setText("No Hay calculos guardados");
        }




//        BaseDatos basaDatos = new BaseDatos(this, BaseDatos.BASA_DATTOS_HISTORIAL, null, 1);
//        arrayList = basaDatos.sacarCalculos();
//
//        HistorialAdapter historialAdapter = new HistorialAdapter(this, arrayList);

    }

    private void ordenarPor(){

        final String [] opcionesOrdenar = new String[]{
                "Calculo: de primero a ultimo","Calculo: de ultimo a primero", "IMC : más alto primero", "IMC : más bajo primero",
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HistorialActivity.this,android.R.layout.simple_spinner_item, opcionesOrdenar);
        Spinner spiner = (Spinner)findViewById(R.id.ordenar_spinener);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);

        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Calculo: de primero a ultimo")){
                    Collections.sort(arrayList, new OrdenarCalculoPrimero ());
                }else if (parent.getItemAtPosition(position).equals("Calculo: de ultimo a primero")){
                    Collections.sort(arrayList, new OrdenarCalculoUltimo());
                }else if (parent.getItemAtPosition(position).equals("IMC : más alto primero")){
                    Collections.sort(arrayList);
                }else{
                    Collections.sort(arrayList, Collections.<Historial>reverseOrder());
                }

                HistorialAdapter historialAdapter = new HistorialAdapter(HistorialActivity.this, arrayList);

                ListView listView = (ListView)findViewById(R.id.historial_list_view);
                listView.setAdapter(historialAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
