package com.metodista.pap.ssm.services;

import com.google.gson.reflect.TypeToken;
import com.metodista.pap.ssm.model.Temporada;

import java.util.List;

public class TemporadaService extends BaseService {
    private List<Temporada> temporadas = null;

    public TemporadaService() {
        super("api/seasons");
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public List<Temporada> cadastrar(Temporada temporada) {
        try {

            String stringUrl = this.getApi() + "?action=insert&name=" + temporada.getName() + "&adminID=" + ConfiguracoesSSM.getInstance().getUsuarioLogado().getIdUsuario();

            this.temporadas = FacadeService.getInstance().getResponseFromService(stringUrl, new TypeToken<List<Temporada>>() {
            }.getType());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return this.temporadas;
    }
}
