package com.jgm.flixnet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Capitulo {

    @Expose
    @SerializedName("idCap")
    private int idCap;

    @Expose
    @SerializedName("temporada")
    private int temporata;

    @Expose
    @SerializedName("capitulo")
    private int capitulo;

    @Expose
    @SerializedName("titulo")
    private String titulo;

    @Expose
    @SerializedName("sinopsis")
    private String sinopsis;

    @Expose
    @SerializedName("puntuacion")
    private float puntuacion;


    public Capitulo() {
    }

    /**
     *
     * @return
     */
    public int getIdCap() {
        return idCap;
    }

    /**
     *
     * @param idCap
     */
    public void setIdCap(int idCap) {
        this.idCap = idCap;
    }

    /**
     *
     * @return
     */
    public int getTemporata() {
        return temporata;
    }

    /**
     *
     * @param temporata
     */
    public void setTemporata(int temporata) {
        this.temporata = temporata;
    }

    /**
     *
     * @return
     */
    public int getCapitulo() {
        return capitulo;
    }

    /**
     *
     * @param capitulo
     */
    public void setCapitulo(int capitulo) {
        this.capitulo = capitulo;
    }

    /**
     *
     * @return
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     *
     * @return
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     *
     * @param sinopsis
     */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    /**
     *
     * @return
     */
    public float getPuntuacion() {
        return puntuacion;
    }

    /**
     *
     * @param puntuacion
     */
    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Capitulo{" +
                "titulo='" + titulo + '\'' +
                '}';
    }
}
