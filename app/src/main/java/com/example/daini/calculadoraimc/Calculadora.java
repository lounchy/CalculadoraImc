package com.example.daini.calculadoraimc;

import android.content.Context;

/**
 * Created by Daini on 20/02/2017.
 */

public class Calculadora {
    private String resultado;
    private Context context;


    public Calculadora(Context context, String resultado) {
        this.resultado = resultado;
        this.context = context;
    }

    public Calculadora(String resultado) {
        this.resultado = resultado;
    }

    public Calculadora(float altura, float peso){
        float res = 0;
        altura = altura / 100;
        res = (peso)/(altura*altura);
        resultado = String.valueOf(res);
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
