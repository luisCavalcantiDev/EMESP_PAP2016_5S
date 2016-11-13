package com.metodista.pap.ssm.services;

import com.google.gson.reflect.TypeToken;
import com.metodista.pap.ssm.model.Usuario;

import java.util.List;

public class UsuarioService extends BaseService {
    private List<Usuario> usuarios = null;

    public UsuarioService() {
        super("api/players");
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Usuario> autenticar(Usuario usuario) throws Exception {
        try {

            String stringUrl = this.getApi() + "?action=select&email=" + usuario.getEmail() + "&pass=" + usuario.getPass();

            this.usuarios = FacadeService.getInstance().getResponseFromService(stringUrl, new TypeToken<List<Usuario>>() {
            }.getType());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return this.usuarios;
    }

    public List<Usuario> cadastrarUsuario(Usuario usuario) throws Exception {
        try {

            String stringUrl = this.getApi() + "?action=insert&name=" + usuario.getName() + "&email=" + usuario.getEmail() + "&pass=" + usuario.getPass();

            this.usuarios = FacadeService.getInstance().getResponseFromService(stringUrl, new TypeToken<List<Usuario>>() {
            }.getType());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return this.usuarios;
    }
}
