package com.example.daini.calculadoraimc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.daini.calculadoraimc.Models.Historial;
import com.example.daini.calculadoraimc.R;
import com.example.daini.calculadoraimc.Utils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daini on 22/02/2017.
 */

public class HistorialAdapter extends ArrayAdapter<Historial> {


    public HistorialAdapter(Context context, List<Historial> arrayList) {
        super(context, 0, arrayList);

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View elementoVista = convertView;
        if (elementoVista == null){
            elementoVista = LayoutInflater.from(getContext()).inflate(R.layout.pintar_historial, parent, false);
        }else {
            elementoVista = convertView;
        }
        TextView calculoNR = (TextView)elementoVista.findViewById(R.id.calculo_nr_historial_tv);
        TextView resultado =(TextView)elementoVista.findViewById(R.id.resultado_histroial_tv);

        Historial historial = getItem(position);


        calculoNR.setText(String.valueOf(historial.getNrCalculo()));
        resultado.setText(historial.getResultadoImc());

//        calculoNR.setText(arrayList.get(position).getNrCalculo());
//        resultado.setText(arrayList.get(position).getResultadoImc());

        return elementoVista;
    }


}
