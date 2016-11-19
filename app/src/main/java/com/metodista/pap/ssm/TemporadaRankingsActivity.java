package com.metodista.pap.ssm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.metodista.pap.ssm.adapters.RankingArrayAdapter;
import com.metodista.pap.ssm.dao.RankingDao;
import com.metodista.pap.ssm.dao.SSMDataBase;
import com.metodista.pap.ssm.model.Ranking;
import com.metodista.pap.ssm.model.Temporada;
import com.metodista.pap.ssm.services.RankingService;

import java.util.List;

public class TemporadaRankingsActivity extends AppCompatActivity {

    private RankingService service = new RankingService();
    private RankingArrayAdapter adapter;
    private Temporada temporada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporada_rankings);

        if (getIntent().getExtras() != null) {
            temporada = new Temporada();
            temporada.setName(getIntent().getExtras().getString("nome_temp"));
            temporada.setIdTemporada(getIntent().getExtras().getString("id_temp"));

            String texto = ((TextView) findViewById(R.id.tempRnkTexto)).getText().toString();
            ((TextView) findViewById(R.id.tempRnkTexto)).setText(texto + " " + temporada.getName());

            this.carregarDados();
        }
    }

    private void carregarDados() {
        RankingDao rankingDao = (new SSMDataBase(TemporadaRankingsActivity.this, SSMDataBase.DAO_RANKING)).getRankingDao();
        List<Ranking> rankings = rankingDao.getRankingsPorTemporada(this.temporada.getIdTemporada());
        if (rankings != null) {
            adapter = new RankingArrayAdapter(getBaseContext(), 0, rankings);
            ((ListView) findViewById(R.id.tempRnkList)).setAdapter(adapter);
        }
    }
}
