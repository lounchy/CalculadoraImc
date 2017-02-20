package com.example.daini.calculadoraimc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Daini on 19/02/2017.
 */

public class EscuchaButton extends MainActivity implements View.OnClickListener {
    CalculadoraActivity calculadoraActivity;
    String resultados;

        //Constructor
    public EscuchaButton(CalculadoraActivity calculadoraActivity) {
        this.calculadoraActivity = calculadoraActivity;
    }

    @Override
    public void onClick(View v) {
        //Casos de todos botones
        switch (v.getId()){

            case R.id.calcular_button:
                caluclarImc();
                break;
            case R.id.guardar_resultado_button:
                guardarResultados();
                break;
        }
    }
    //Methodo calcular imc
    private void caluclarImc(){

        //defino edit Texts
        EditText et_altura = (EditText)calculadoraActivity.findViewById(R.id.introducir_altura_et);
        EditText et_peso = (EditText)calculadoraActivity.findViewById(R.id.introducir_peso_et);
        //Saco texto de edittext
        String str_altura = et_altura.getText().toString();
        String  str_peso = et_peso.getText().toString();
        //De string a float
        float f_altura = Float.parseFloat(str_altura);
        float f_peso = Float.parseFloat(str_peso);
        //declaro Calculadora
        Calculadora calculadora = new Calculadora(f_altura, f_peso);

        //saco resultado de clase calculadora
        String resultado1 = calculadora.getResultado();

        //Defino text view
        TextView tv_res = (TextView)calculadoraActivity.findViewById(R.id.text_result);
        //pinto resultado
        tv_res.setText(resultado1);
    }

    private void guardarResultados(){
        //Declaro calculadora
        Calculadora calculadora = new Calculadora(resultados);
        //declaro base de datos
        BasaDatos basaDatos = new BasaDatos(this, BasaDatos.NOMBRE_TABLA, null, 1);

        //seteo nuevo resultado
        calculadora.setResultado(resultados);
        //paso a base de datos
        basaDatos.insertarResultados(calculadora);
        //lanco actividad donde se guarda resultados
        calculadoraActivity.startActivity(new Intent(calculadoraActivity, ListaResultadosActivity.class));
    }

}
