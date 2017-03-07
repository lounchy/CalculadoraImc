package com.example.daini.calculadoraimc.Ordenar;

import com.example.daini.calculadoraimc.Activity.HistorialActivity;
import com.example.daini.calculadoraimc.Models.Historial;

import java.util.Comparator;

/**
 * Created by Daini on 03/03/2017.
 */

public class OrdenarCalculoUltimo implements Comparator<Historial> {
    @Override
    public int compare(Historial o1, Historial o2) {

          return o2.getNrCalculo() - o1.getNrCalculo();


    }

}
