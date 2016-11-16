package com.metodista.pap.ssm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temporada {

    private Long _id;

    @SerializedName("id")
    @Expose
    private String idTemporada;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("adminID")
    @Expose
    private String adminID;

    public Temporada(){
        this._id = null;
        this.name = "";
        this.adminID = "";
        this.idTemporada = "";
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getIdTemporada() {
        return this.idTemporada;
    }

    public void setIdTemporada(String idTemporada) {
        this.idTemporada = idTemporada;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
}
