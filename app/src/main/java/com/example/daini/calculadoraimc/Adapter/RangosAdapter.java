package com.example.daini.calculadoraimc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daini.calculadoraimc.Models.Rangos;
import com.example.daini.calculadoraimc.R;

import java.util.ArrayList;

/**
 * Created by Daini on 23/02/2017.
 */

public class RangosAdapter extends ArrayAdapter<Rangos> {

    public RangosAdapter(Context context, ArrayList<Rangos> arrayList){
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View elementoVista = convertView;
        if (elementoVista == null)
        {
            elementoVista = LayoutInflater.from(getContext()).inflate(R.layout.pintar_rangos, parent, false);
        }else{
            elementoVista = convertView;
        }

        ImageView image = (ImageView)elementoVista.findViewById(R.id.imagen_pintar_rangos);
        TextView clasificacion = (TextView)elementoVista.findViewById(R.id.clasificacion_pintar_rangos);
        TextView valor = (TextView)elementoVista.findViewById(R.id.valor_pintar_rangos);

        Rangos datoActual = getItem(position);

        image.setImageResource(datoActual.getRangosImagen());
        clasificacion.setText(datoActual.getRangosClasificacion());
        valor.setText(datoActual.getRangosValor());

        return elementoVista;
    }
}
