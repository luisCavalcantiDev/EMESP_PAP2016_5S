package com.metodista.pap.ssm.services;

public abstract class BaseService {

    private static final String _BASE_URL = "http://10.0.2.2:4000/";
    //private static final String _BASE_URL = "http://pap-ssm.sytes.net/";

    private String api;

    public BaseService(String api) {
        this.api = _BASE_URL + api;
    }

    public static String getBaseUrl() {
        return _BASE_URL;
    }

    public String getApi() {
        return this.api;
    }
}
