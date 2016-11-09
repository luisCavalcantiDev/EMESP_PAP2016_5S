package com.metodista.pap.ssm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resultado {

    @SerializedName("processado")
    @Expose
    private Boolean processado;

    @SerializedName("msg")
    @Expose
    private String MsgErro;

    public Boolean getProcessado() {
        return processado;
    }

    public void setErro(Boolean erro) {
        this.processado = erro;
    }

    public String getMsgErro() {
        return MsgErro;
    }

    public void setMsgErro(String msgErro) {
        this.MsgErro = msgErro;
    }
}
