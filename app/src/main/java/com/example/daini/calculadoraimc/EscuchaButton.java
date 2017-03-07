package com.example.daini.calculadoraimc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daini.calculadoraimc.Activity.CalculadoraActivity;
import com.example.daini.calculadoraimc.Activity.HistorialActivity;
import com.example.daini.calculadoraimc.Activity.LoginActivity;
import com.example.daini.calculadoraimc.Activity.MainActivity;
import com.example.daini.calculadoraimc.Activity.RangosActivity;
import com.example.daini.calculadoraimc.Activity.RegistroActivity;
import com.example.daini.calculadoraimc.Activity.ResultadosActivity;
import com.example.daini.calculadoraimc.Models.Historial;
import com.example.daini.calculadoraimc.Models.Usuario;

/**
 * Created by Daini on 19/02/2017.
 */

public class EscuchaButton extends MainActivity implements View.OnClickListener {
    private CalculadoraActivity calculadoraActivity;
    private ResultadosActivity resultadosActivity;
    private RegistroActivity registroActivity;
    private LoginActivity loginActivity;
    private HistorialActivity historialActivity;

    //Constructores
    public EscuchaButton(CalculadoraActivity calculadoraActivity) {this.calculadoraActivity = calculadoraActivity;}

    public EscuchaButton(ResultadosActivity resultadosActivity) {this.resultadosActivity = resultadosActivity;}

    public EscuchaButton(RegistroActivity registroActivity) {this.registroActivity = registroActivity;}

    public EscuchaButton(LoginActivity loginActivity) {this.loginActivity = loginActivity;}

    public EscuchaButton(HistorialActivity historialActivity) {this.historialActivity = historialActivity;}

    @Override
    public void onClick(View v) {

        //Casos de todos botones
        switch (v.getId()) {

            case R.id.calcular_button:
                caluclarImc(v);
                break;
            case R.id.consultar_rangos_button:
                resultadosActivity.startActivity(new Intent(resultadosActivity, RangosActivity.class));
                break;
            case R.id.registrar_usuario_buton:
                registrarUsuario();
                break;
            case R.id.entrar_button:
                entrar(v);
                break;
            case R.id.pasar_al_registro:
                loginActivity.startActivity(new Intent(loginActivity, RegistroActivity.class));
                break;
            case R.id.floating_actoin_buton:
                calculadoraActivity.startActivity(new Intent(calculadoraActivity, HistorialActivity.class));
                break;

        }
    }

    //Methodo calcular imc
    private void caluclarImc(View view) {

        //defino edit Texts
        EditText et_altura = (EditText) calculadoraActivity.findViewById(R.id.introducir_altura_et);
        EditText et_peso = (EditText) calculadoraActivity.findViewById(R.id.introducir_peso_et);
        //Saco texto de edittext
        String str_altura = et_altura.getText().toString();
        String str_peso = et_peso.getText().toString();

        //declaro Utils
        Utils utils = new Utils();

        //saco resultado de clase utils
        if (!str_altura.equals("") && !str_peso.equals("")) {//Campos rellenados bien
            Log.d(getClass().getCanonicalName(), "CAMPOS BIEN");

            //paso de String a FLoat
            float altura = Float.parseFloat(str_altura);
            float peso = Float.parseFloat(str_peso);

            //La altura introducida tiene que ser entre 100 y 250, peso entre 20-200
            if (peso < 20 || peso > 200 || altura < 100 || altura > 250) {//Altura y peso introducida mal
                Log.d(getClass().getCanonicalName(), "ALGO VA MAL");
                //Comprobar altura
                if (altura < 100 || altura > 250) {
                    Toast.makeText(calculadoraActivity, "Comprueba el valor de altura", Toast.LENGTH_SHORT).show();
                    Log.d(getClass().getCanonicalName(), "ALTURA INTRODUZIDO MAL");
                    //Comprobar peso
                } else if (peso < 20 || peso > 200) {
                    Toast.makeText(calculadoraActivity, "Comprueba el valor de peso", Toast.LENGTH_SHORT).show();
                    Log.d(getClass().getCanonicalName(), "PESO INTRODUZIDO MAL");
                }
            } else {//Altura y peso introducida bien
                Log.d(getClass().getCanonicalName(), "TODO BIEN");

                float float_resultado = utils.calcularImc(str_altura, str_peso);
                String resultado = String.format("%.1f", float_resultado);//paso de float a string y formateo (20.282838 >>20.3)

                int calculoNr;//defino variable
                calculoNr = nrCalculo(view);//asigno nuevo valor
                Log.d(getClass().getCanonicalName(), "calculo nr " + calculoNr);

                //Añado valores a Basa de datos HISTORIAL
                BaseDatos basaDatos = new BaseDatos(calculadoraActivity, BaseDatos.BASA_DATTOS_HISTORIAL, null, 1);

                SharedPreferences sharedPreferences = calculadoraActivity.getSharedPreferences("pref", Context.MODE_PRIVATE);
                int usrID = sharedPreferences.getInt("usrID", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("calculoNr", calculoNr);
                editor.commit();


                Historial historial = new Historial(usrID);
                historial.setNrCalculo(calculoNr);
                historial.setResultadoImc(resultado);
                Log.e(getClass().getCanonicalName(), " calcular id " + usrID);
                basaDatos.insertarCalculoImc(historial);//guardo en base de datos
                basaDatos.close();



                //Lanzo actividad resultados
                Intent intent = new Intent(calculadoraActivity, ResultadosActivity.class);
                intent.putExtra("Resultado", float_resultado);
                calculadoraActivity.startActivity(intent);
            }//Fin de comprobar altura peso
        } else {//campos vacios
            Toast.makeText(calculadoraActivity, "No dejas campos vacíos", Toast.LENGTH_SHORT).show();
            Log.d(getClass().getCanonicalName(), "CAMPOS VA MAL");
        }//Fin de comprobar campos

    }
    //Registrar nuevo usuario
    private void registrarUsuario() {

        SharedPreferences sharedPreferences = registroActivity.getSharedPreferences("pref", Context.MODE_PRIVATE);
        sharedPreferences.getBoolean("usuarioLogeado", false);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Declaro todos EditText
        EditText et_nombre = (EditText) registroActivity.findViewById(R.id.nombre_registro_et);
        EditText et_altura = (EditText) registroActivity.findViewById(R.id.altura_registro_et);
        EditText et_usuario = (EditText) registroActivity.findViewById(R.id.usuario_registro_et);
        EditText et_contrasenia = (EditText) registroActivity.findViewById(R.id.contrasena_registro_et);

        //Paso a String
        String str_nombre = et_nombre.getText().toString();
        String str_altura = et_altura.getText().toString();
        String str_usuario = et_usuario.getText().toString();
        String str_contrasenia = et_contrasenia.getText().toString();

        //Declaro clase Usuario con 1 variable para comprobar si nombre usuario esta libre
        Usuario usuario = new Usuario(str_usuario);

        //Declaro Base datos
        BaseDatos baseDatos = new BaseDatos(registroActivity, BaseDatos.BASA_DATTOS_USUARIO, null, 1);

        //Comprobar si campos rellenados bien
        if (!str_nombre.equals("") && !str_altura.equals("") && !str_usuario.equals("") && !str_contrasenia.equals("")) {//campos rellenados bie
            if (!baseDatos.existeNombreUsuario(usuario)) {//nombre usuario libre(no existe)
                usuario = new Usuario(str_usuario, str_contrasenia);

                usuario.setNombre(str_nombre);
                usuario.setAltura(str_altura);
                usuario.setUsuario(str_usuario);
                usuario.setContrasenia(str_contrasenia);
                baseDatos.insertarUsuario(usuario);
                editor.putBoolean("usuarioLogeado",false);
                editor.putInt("usrID", baseDatos.sacarId(usuario));
                editor.commit();
                baseDatos.close();
                Log.e(getClass().getCanonicalName(), "Registrado id " + baseDatos.sacarId(usuario));

//                editor.putString("usuarioAltura",str_altura );//Aqui quiero guardar altura ????
//                editor.commit();

                //Lanzo LoginActivity
                registroActivity.startActivity(new Intent(registroActivity, LoginActivity.class));
            } else {//Nombre usuario no esta libre
                Toast.makeText(registroActivity, "Nombre de Usuario " + str_usuario + " No está Libre", Toast.LENGTH_SHORT).show();
            }//Fin de comprobar usuario
        } else { //Campos rellenados mal
            Toast.makeText(registroActivity, "Comprueba todos campos", Toast.LENGTH_SHORT).show();
        }//fin de comprobar campos

    }

    //Entrar en la cuenta
    //Comprobar si usuario esta logeado o no
    public void entrar(View view) {
        SharedPreferences sharedPreferences = loginActivity.getSharedPreferences("pref", Context.MODE_PRIVATE);
        sharedPreferences.getBoolean("usuarioLogeado", false);
//        sharedPreferences.getString("usuarioAltura","");//para sacar altura ???
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Declaro EditText
        EditText et_usuario = (EditText) loginActivity.findViewById(R.id.usuario_login_et);
        EditText et_contrasenia = (EditText) loginActivity.findViewById(R.id.contrasena_login_et);

        //Paso EditText a String
        String str_usuario = et_usuario.getText().toString();
        String str_contrasenia = et_contrasenia.getText().toString();

        //Declaro clase Usuario con 2 variables para comprobar nombre usuario y contraseña
        Usuario usuario = new Usuario(str_usuario, str_contrasenia);

        //Declaro BaseDatos
        BaseDatos baseDatos = new BaseDatos(loginActivity, BaseDatos.BASA_DATTOS_USUARIO, null, 1);

        //Comprobar si campos rellenados bien
        if (!str_usuario.equals("") && !str_contrasenia.equals("")) {//Campos rellenados bien
            if (baseDatos.existeCuenta(usuario)) {//cuenta existe

                editor.putBoolean("usuarioLogeado", true);//usuario logeado
                editor.putString("usuario", str_usuario);//saco nombre usuario para saludar
                editor.putString("pw", str_contrasenia);
                editor.putInt("usrID", baseDatos.sacarId(new Usuario(str_usuario, str_contrasenia)));
                editor.commit();

                Log.e(getClass().getCanonicalName(), " id login " + baseDatos.sacarId(new Usuario(str_usuario, str_contrasenia)));
                //Lanzo actividad Calculadora
                loginActivity.startActivity(new Intent(loginActivity, CalculadoraActivity.class));

            } else {//no existe cuenta
                if (!baseDatos.existeNombreUsuario(usuario)) {//Usuario no exista
                    Toast.makeText(loginActivity, "Usuario no existe", Toast.LENGTH_SHORT).show();
                } else if (!baseDatos.contraseniaCorecta(usuario)) {//Contraseña incorecta
                    intentos(view); //contador de intentos
                }
            }//Fin de comprobar cuenta
        } else {//Campos rellenados mal
            Toast.makeText(loginActivity, "Comprueba todos campos", Toast.LENGTH_SHORT).show();
        }//Fin de comprobar campos
    }

    //Contador de intentos
    private void intentos(View view) {
        int intentos = 2; //2-1-0
        if (view.getTag() != null) //no es primera vez que se toca
        {
            intentos = (int) view.getTag();
            intentos = intentos - 1;
            view.setTag(intentos);
        }
        view.setTag(intentos);//primera vez que se toca
        if (intentos > 0) {//comprobar intentos resintentes
            Toast.makeText(loginActivity, "Contraseña incorecta! Quedan " + intentos + " intentos", Toast.LENGTH_SHORT).show();
        } else {
            //termino applicacion (salgo)
            loginActivity.finishAffinity();
        }

    }
    //Contador de calculos
    private int nrCalculo(View view){
       int calculoNr = 1;
        if (view.getTag()!=null){//no es primera vez que haga calculo
            calculoNr = (int)view.getTag();
            calculoNr = calculoNr + 1;
            view.setTag(calculoNr);
        }
        view.setTag(calculoNr);//es primera vez que se hace el calculo
        return calculoNr;
    }

}
