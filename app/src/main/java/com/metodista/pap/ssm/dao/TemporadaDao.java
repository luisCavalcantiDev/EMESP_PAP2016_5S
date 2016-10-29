package com.metodista.pap.ssm.dao;

public class TemporadaDao {

    private final static TemporadaDao instance = new TemporadaDao();

    private TemporadaDao(){}

    public static TemporadaDao getInstance(){
        return instance;
    }
}
