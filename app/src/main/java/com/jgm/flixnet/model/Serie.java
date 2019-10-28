package com.jgm.flixnet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Serie {
    @Expose //Permite trabajar con el atributo
    @SerializedName("idSer") //Enlaza el atributo con el parámetro del JSON
    private int idSer;

    @Expose
    @SerializedName("titulo")
    private String titulo;

    @Expose
    @SerializedName("genero")
    private String genero;

    @Expose
    @SerializedName("sinopsis")
    private String argumento;

    @Expose
    @SerializedName("cartel")
    private String cartel;


    @Expose
    @SerializedName("fecha_estreno")
    private String estreno;

    @Expose
    @SerializedName("punt_imdb")
    private float puntuacion;

    public Serie() {
    }

    /**
     * @return
     */
    public int getIdSer() {
        return idSer;
    }

    /**
     * @param idSer
     */
    public void setIdSer(int idSer) {
        this.idSer = idSer;
    }

    /**
     * @return
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return
     */
    public String getArgumento() {
        return argumento;
    }

    /**
     * @param argumento
     */
    public void setArgumento(String argumento) {
        this.argumento = argumento;
    }

    /**
     * @return
     */
    public String getEstreno() {
        return estreno;
    }

    /**
     * @param estreno
     */
    public void setEstreno(String estreno) {
        this.estreno = estreno;
    }

    /**
    * @return
    */
    public String getCartel() {
        return cartel;
    }

    /**
     * @param cartel
     */
    public void setCartel(String cartel) {
        this.cartel = cartel;
    }

    /**
     * @return
     */
    public float getPuntuacion() {
        return puntuacion;
    }

    /**
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
        return "Serie{" +
                "titulo='" + titulo + '\'' +
                '}';
    }
}
