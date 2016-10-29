package com.metodista.pap.ssm.proxy;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import com.metodista.pap.ssm.model.Usuario;

public class UsuarioProxy extends AsyncTask<Object, Object, Boolean>{

    //private final String _URL = "http://localhost:3000/ssm/api/usuario";
    private final String _URL = "http://10.0.2.2:3000/ssm/api/usuario";
    private final String _TOKEN = ""; //TODO: implementar segurança no acesso WS

    @Override
    protected Boolean doInBackground(Object... params) {
        Usuario usuario = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url =  new URL(this._URL);
            urlConnection = (HttpURLConnection)url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Type listType = new TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> list = gson.fromJson(conteudo,listType);

            String id = list.get(0).getId();

        }catch(Exception e){
            throw  new RuntimeException(e);
        }
        finally{
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
