package com.metodista.pap.ssm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ranking {

    private Long _id;

    @SerializedName("userID")
    @Expose
    private String usuario;

    @SerializedName("seasonID")
    @Expose
    private String temporada;

    @SerializedName("campeonato")
    @Expose
    private String campeonato;

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("points")
    @Expose
    private int pontos;

    @SerializedName("id")
    @Expose
    private String idRanking;

    public Ranking(){
        this._id = null;
        this.campeonato = "";
        this.time = "";
        this.usuario = "";
        this.temporada = "";
        this.idRanking = "";
        this.pontos = 0;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public String getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(String campeonato) {
        this.campeonato = campeonato;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getIdRanking() {
        return idRanking;
    }

    public void setIdRanking(String idRanking) {
        this.idRanking = idRanking;
    }
}
