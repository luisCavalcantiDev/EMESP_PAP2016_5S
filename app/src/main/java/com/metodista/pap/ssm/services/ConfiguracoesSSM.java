package com.metodista.pap.ssm.services;

import com.metodista.pap.ssm.model.Usuario;

public final class ConfiguracoesSSM {

    private static final ConfiguracoesSSM INSTANCE = new ConfiguracoesSSM();

    private Usuario usuarioLogado;

    private ConfiguracoesSSM() {
    }

    public static ConfiguracoesSSM getInstance() {
        return INSTANCE;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Usuario getUsuarioLogado() {
        return this.usuarioLogado;

    }
}
