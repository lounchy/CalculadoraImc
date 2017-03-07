package com.example.daini.calculadoraimc.Models;

/**
 * Created by Daini on 23/02/2017.
 */

public class Rangos {
    private int rangosImagen;
    private String rangosClasificacion;
    private String rangosValor;

    public Rangos( String rangosValor, String rangosClasificacion,int rangosImagen) {

        this.rangosClasificacion = rangosClasificacion;
        this.rangosValor = rangosValor;
        this.rangosImagen = rangosImagen;
    }

    public int getRangosImagen() {
        return rangosImagen;
    }

    public String getRangosClasificacion() {
        return rangosClasificacion;
    }

    public String getRangosValor() {
        return rangosValor;
    }
}
