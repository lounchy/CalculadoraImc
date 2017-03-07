package com.example.daini.calculadoraimc.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.daini.calculadoraimc.Adapter.RangosAdapter;
import com.example.daini.calculadoraimc.Models.Rangos;
import com.example.daini.calculadoraimc.R;

import java.util.ArrayList;

public class RangosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintar_listas);
        Log.d(getClass().getCanonicalName(), "Estoy en RangosActivity");
        ArrayList<Rangos> arrayList = new ArrayList<>();
        arrayList.add(new Rangos("<18.5", "Bajo Peso", R.drawable.image_imc_bajo_peso));
        arrayList.add(new Rangos("18.5 - 25", "Peso Ideal", R.drawable.image_imc_peso_ideal));
        arrayList.add(new Rangos("25 - 30", "Sobrepeso", R.drawable.image_imc_sobrepeso));
        arrayList.add(new Rangos("30 - 40", "Obesidad", R.drawable.image_imc_obesidad));
        arrayList.add(new Rangos(">40", "Obesidad Morbida", R.drawable.image_imc_obesidad_morbida));

        RangosAdapter rangosAdapter = new RangosAdapter(this, arrayList);
        ListView listView = (ListView)findViewById(R.id.rangos_list_view);
        listView.setAdapter(rangosAdapter);

    }
}
