package com.example.daini.calculadoraimc.Models;

/**
 * Created by Daini on 28/02/2017.
 */

public class Historial implements Comparable<Historial>{
    private String resultadoImc;
    private int nrCalculo;
    private int usrID;

    public Historial(int usrID, int nrCalculo, String resultadoImc) {
        this.usrID = usrID;
        this.nrCalculo = nrCalculo;
        this.resultadoImc = resultadoImc;
    }

    public Historial(int nrCalculo, String resultadoImc) {
        this.resultadoImc = resultadoImc;
        this.nrCalculo = nrCalculo;
    }

    public Historial(int usrID) {
        this.usrID = usrID;
    }


    public String getResultadoImc() {
        return resultadoImc;
    }

    public void setResultadoImc(String resultadoImc) {
        this.resultadoImc = resultadoImc;
    }

    public int getNrCalculo() {
        return nrCalculo;
    }

    public void setNrCalculo(int nrCalculo) {
        this.nrCalculo = nrCalculo;
    }

    public int getUsrID() {
        return usrID;
    }

    public void setUsrID(int usrID) {
        this.usrID = usrID;
    }

    @Override
    public int compareTo(Historial o) {
        String resultado1 = this.getResultadoImc();
        String resultado2 = o.getResultadoImc();




        return resultado2.compareTo(resultado1);
    }
}
