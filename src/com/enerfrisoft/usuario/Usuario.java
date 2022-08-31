/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.usuario;

/**
 *
 * @author sebastianaf
 */
public class Usuario {

    private String username;
    private String nombre;
    private String pass;
    private String identidad;
    private String tipo_usuario;
    private String estado;
    private String server;

    public Usuario() {
        this.username = "";
        this.nombre = "";
        this.pass = "";
        this.identidad = "";
        this.tipo_usuario = "";
        this.estado = "";
        this.server = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String fecha_creacion) {
        this.identidad = fecha_creacion;
    }

    
    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
