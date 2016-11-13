package com.metodista.pap.ssm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temporada {

    private Long idTemporada;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    public Long getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(Long idTemporada) {
        this.idTemporada = idTemporada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
