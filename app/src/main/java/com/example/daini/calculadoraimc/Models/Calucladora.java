package com.example.daini.calculadoraimc.Models;

import android.content.Context;

/**
 * Created by Daini on 23/02/2017.
 */

public class Calucladora {
    private Context context;
    private int limiteImc;
    private String clasificacionImc;
    private int resultadoImagen;


    public Calucladora(Context context, int limiteImc, String clasificacionImc) {
        this.context = context;
        this.limiteImc = limiteImc;
        this.clasificacionImc = clasificacionImc;
    }

    public Calucladora(int limiteImc, String clasificacionImc, int resultadoImagen) {
        this.limiteImc = limiteImc;
        this.clasificacionImc = clasificacionImc;
        this.resultadoImagen = resultadoImagen;
    }

    public String getClasificacionImc() {
        return clasificacionImc;
    }

    public void setClasificacionImc(String clasificacionImc) {
        this.clasificacionImc = clasificacionImc;
    }

    public int getLimiteImc() {
        return limiteImc;
    }

    public void setLimiteImc(int limiteImc) {
        this.limiteImc = limiteImc;
    }

    public int getResultadoImagen() {
        return resultadoImagen;
    }

    public void setResultadoImagen(int resultadoImagen) {
        this.resultadoImagen = resultadoImagen;
    }
}
