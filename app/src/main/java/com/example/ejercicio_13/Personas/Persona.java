package com.example.ejercicio_13.Personas;

public class Persona {
    String nombre,apellidos,correo,dirrecion;
    int edad;
    public Persona(String nombre,String apellidos,int edad,String correo,String dirreccion){
     this.nombre=nombre;
        this.apellidos=nombre;
        this.correo=nombre;
        this.edad=edad;
        this.dirrecion=dirreccion;
    }
    public Persona(){}

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getDirrecion() {
        return dirrecion;
    }
    public void setDirrecion(String dirrecion) {
        this.dirrecion = dirrecion;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
}
