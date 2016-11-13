package com.metodista.pap.ssm.services;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class FacadeService {

    private static final FacadeService INSTANCE = new FacadeService();

    private FacadeService(){
    }

    public static FacadeService getInstance(){
        return INSTANCE;
    }

    public <T> T getResponseFromService(String stringUrl, Type objT) throws Exception {
        T response = null;
        String json = "";

        try{
            URL url = getURL(stringUrl);
            if(url != null){
                HttpURLConnection openHttpUrlConnection = getURLConnection(url);
                if(openHttpUrlConnection != null){
                    json = getJsonFromHttpURLConnection(openHttpUrlConnection);
                    if(json.equals("") == false){
                        response = getObjectFromJson(json, objT);

                        if(response == null){
                            throw new Exception("response null --> não convetido para " + objT.getClass().toString() + " --> json: " + json);
                        }
                    }
                }
            }

        }catch (Exception e){
            throw e;
        }

        return response;
    }

    private URL getURL(String stringUrl) throws MalformedURLException {
        return (new URL(stringUrl));
    }

    private HttpURLConnection getURLConnection(URL url) throws IOException {
        HttpURLConnection urlConnection = null;

        urlConnection = (HttpURLConnection) url.openConnection();

        return urlConnection;
    }

    private String getJsonFromHttpURLConnection(HttpURLConnection openHttpUrlConnection) throws IOException {
        InputStream in = null;
        Scanner s = null;
        String conteudo = "";

        try {
            in = new BufferedInputStream(openHttpUrlConnection.getInputStream());
            s = new Scanner(in);
            conteudo = s.useDelimiter("\\A").next();
        }catch (Exception e){
            conteudo = "";
        }finally {
            if (openHttpUrlConnection != null) {
                openHttpUrlConnection.disconnect();
            }
            if (in != null) {
                in.close();
            }
            if (s != null) {
                s.close();
            }
        }

        return conteudo;
    }

    private <T> T getObjectFromJson(String json, Type objT){
        Gson gson = null;
        T result = null;

        try {
            gson = new Gson();
            result = gson.fromJson(json, objT);

        } catch (Exception e) {
            result = null;
        }

        return result;
    }
}
