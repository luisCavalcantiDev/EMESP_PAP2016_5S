package com.metodista.pap.ssm.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.metodista.pap.ssm.model.Usuario;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioService {

    private static final String _BASE_URL = "http://10.0.2.2:4000/api/players";
    //private static final String _BASE_URL = "http://pap-ssm.sytes.net/api/players";
    private List<Usuario> usuarios = null;

    public List<Usuario> autenticar(Usuario usuario) throws Exception {
        List<Usuario> result = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        Scanner s = null;

        try {

            URL url = new URL(_BASE_URL + "?action=select&email=" + usuario.getEmail() + "&pass=" + usuario.getPass());
            urlConnection = (HttpURLConnection) url.openConnection();

            in = new BufferedInputStream(urlConnection.getInputStream());
            s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            result = gson.fromJson(conteudo, new TypeToken<List<Usuario>>() {}.getType());

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (in != null) {
                in.close();
            }

            if (s != null) {
                s.close();
            }
        }

        if (result != null) {
            if (result.size() > 0) {
                usuarios = new ArrayList<>();
                usuarios.add(result.get(0));
            }else{
                throw new Exception("Usuário não autenticado.");
            }
        }

        return this.usuarios;
    }

    public List<Usuario> cadastrarUsuario(Usuario usuario) throws Exception {
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        Scanner s = null;
        List<Usuario> result = null;

        try {

            URL url = new URL(_BASE_URL + "?action=insert&name=" + usuario.getName() + "&email=" + usuario.getEmail() + "&pass=" + usuario.getPass());
            urlConnection = (HttpURLConnection) url.openConnection();

            in = new BufferedInputStream(urlConnection.getInputStream());
            s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            result = gson.fromJson(conteudo, new TypeToken<List<Usuario>>() {}.getType());

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (in != null) {
                in.close();
            }

            if (s != null) {
                s.close();
            }
        }

        if (result != null) {
            if (result.size() > 0) {
                usuarios = new ArrayList<>();
                usuarios.add(result.get(0));
            }else{
                throw new Exception("Usuário não cadastrado.");
            }
        }

        return this.usuarios;
    }
}
