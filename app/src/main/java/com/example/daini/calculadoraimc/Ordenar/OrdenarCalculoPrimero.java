package com.example.daini.calculadoraimc.Ordenar;

import com.example.daini.calculadoraimc.Models.Historial;

import java.util.Comparator;

/**
 * Created by Daini on 03/03/2017.
 */

public class OrdenarCalculoPrimero implements Comparator<Historial> {
    @Override
    public int compare(Historial o1, Historial o2) {


        return o1.getNrCalculo() - o2.getNrCalculo();
    }
}
