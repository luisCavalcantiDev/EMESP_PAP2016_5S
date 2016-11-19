package com.metodista.pap.ssm.services;

import com.google.gson.reflect.TypeToken;
import com.metodista.pap.ssm.model.Ranking;

import java.util.List;

public class RankingService extends BaseService {
    private List<Ranking> rankings = null;

    public RankingService() {
        super("api/ranking");
    }

    public List<Ranking> getRankins() {
        return rankings;
    }

    public List<Ranking> cadastrar(Ranking ranking) {
        try {

            String stringUrl = this.getApi() + "?action=insert&campeonato=" + ranking.getCampeonato() + "&time=" + ranking.getTime() + "&userID=" + ranking.getUsuario() + "&seasonID=" + ranking.getTemporada() + "&points=" + ranking.getPontos();

            this.rankings = FacadeService.getInstance().getResponseFromService(stringUrl, new TypeToken<List<Ranking>>() {
            }.getType());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return this.rankings;
    }
}
