package com.example.listaisrael;

public class Freestyler {
    private String nombre;
    private String resultado;
    private int imagen; // el recurso de la imagen: R.drawable.algo

    public Freestyler(String nombre, String resultado, int imagen) {
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

    public int getImagen() {
        return imagen;
    }
}
