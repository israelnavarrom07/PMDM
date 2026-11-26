package com.example.listaisrael;

public class Freestyler {
    private String nombre;
    private String resultado;
    private String imagen;

    public Freestyler(String nombre, String resultado, String imagen) {
        this.nombre = nombre;
        this.resultado = resultado;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getResultado() {
        return resultado;
    }

    public String getImagen() {
        return imagen;
    }
}
