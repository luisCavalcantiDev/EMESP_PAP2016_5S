package com.metodista.pap.ssm.servicos;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Scanner;

public abstract class ProxySSM {

    private final String _BASE_URL = "http://10.0.2.2:3000/ssm/api/";

    private String _URL;
    private HttpURLConnection urlConnection = null;
    private GsonBuilder gsonBuilder = null;
    private Gson gson = null;

    public ProxySSM(String URL_API){
        this._URL = this._BASE_URL + URL_API;

        this.gsonBuilder = new GsonBuilder();
        this.gson = gsonBuilder.create();
    }

    protected String getJson() throws IOException {
        InputStream in = null;
        Scanner s = null;

        try{
            in = new BufferedInputStream(urlConnection.getInputStream());
            s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Type listType = new TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> list = gson.fromJson(conteudo,listType);


        }catch (Exception e){

        }
        finally {
            if(in != null){
                in.close();
            }
            if(s != null){
                s.close();
            }
        }

        return "";
    }
}
