package com.metodista.pap.ssm.dao;

public class UsuarioDao {

    private final static UsuarioDao instance = new UsuarioDao();

    private UsuarioDao(){}

    public static UsuarioDao getInstance(){
        return instance;
    }
}
