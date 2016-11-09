package com.metodista.pap.ssm.services;

import com.metodista.pap.ssm.model.Usuario;

public final class ConfiguracoesSSM {

    private static final ConfiguracoesSSM instance = new ConfiguracoesSSM();

    private static Usuario usuarioLogado;

    private ConfiguracoesSSM() {
    }

    public static ConfiguracoesSSM getInstance() {
        return instance;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        ConfiguracoesSSM.usuarioLogado = usuarioLogado;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;

    }
}
