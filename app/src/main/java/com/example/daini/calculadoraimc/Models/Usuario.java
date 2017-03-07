package com.example.daini.calculadoraimc.Models;

/**
 * Created by Daini on 24/02/2017.
 */

public class Usuario {
    private int usuarioID;
    private String nombre;
    private String altura;
    private String usuario;
    private String contrasenia;

    public Usuario(int  usuarioID ,String nombre, String altura, String usuario, String contrasenia) {
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.altura = altura;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Usuario(int usuarioID, String altura) {
        this.altura = altura;
        this.usuarioID = usuarioID;
    }

    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Usuario(String usuario) {
        this.usuario = usuario;
    }

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public int getUsuarioID() {return usuarioID;}

    public void setUsuarioID(int usuarioID) {this.usuarioID = usuarioID;}
}
