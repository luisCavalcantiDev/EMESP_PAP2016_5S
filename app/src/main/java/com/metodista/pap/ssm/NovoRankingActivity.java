package com.metodista.pap.ssm;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.metodista.pap.ssm.dao.RankingDao;
import com.metodista.pap.ssm.dao.SSMDataBase;
import com.metodista.pap.ssm.model.Ranking;
import com.metodista.pap.ssm.model.Temporada;
import com.metodista.pap.ssm.services.ConfiguracoesSSM;
import com.metodista.pap.ssm.services.RankingService;
import com.metodista.pap.ssm.utils.AndroidUtil;

import java.util.ArrayList;
import java.util.List;

public class NovoRankingActivity extends AppCompatActivity {

    private RankingService service = new RankingService();
    private Temporada temporada = null;
    private Ranking ranking = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_ranking);

        if (getIntent().getExtras() != null) {
            temporada = new Temporada();
            temporada.setName(getIntent().getExtras().getString("nome_temp"));
            temporada.setIdTemporada(getIntent().getExtras().getString("id_temp"));

            String texto = ((TextView) findViewById(R.id.novoRnkTexto)).getText().toString();
            ((TextView) findViewById(R.id.novoRnkTexto)).setText(texto + " " + temporada.getName());
        }
    }

    public void cadastrarRanking(View view) {
        try {
            this.ranking = new Ranking();
            this.ranking.setCampeonato(AndroidUtil.getTextStringFromField(R.id.novoRnkCampenonato, AndroidUtil.EDIT_TEXT, this));
            this.ranking.setTime(AndroidUtil.getTextStringFromField(R.id.novoRnkTime, AndroidUtil.EDIT_TEXT, this));

            if (this.ranking.getCampeonato().equals("") == true) {
                AndroidUtil.showShortMessage("Digite um nome para o Campeonato!", this);
                return;

            } else if (this.ranking.getTime().equals("") == true) {
                AndroidUtil.showShortMessage("Digite o nome do time!", this);
                return;
            }

            try {
                this.ranking.setPontos(Integer.valueOf(AndroidUtil.getTextStringFromField(R.id.novoRnkPontos, AndroidUtil.EDIT_TEXT, this)));

            } catch (Exception e) {
                AndroidUtil.showShortMessage("Insira os pontos válidos obtidos para lançar no Ranking!", this);
                return;
            }

            this.ranking.setUsuario(ConfiguracoesSSM.getInstance().getUsuarioLogado().getIdUsuario());
            this.ranking.setTemporada(this.temporada.getIdTemporada());
            this.ranking.setIdRanking("");

            RankingDao rankingDao = (new SSMDataBase(NovoRankingActivity.this, SSMDataBase.DAO_RANKING)).getRankingDao();
            rankingDao.cadastrarRanking(this.ranking);

            AndroidUtil.showShortMessage("Ranking cadastrado com sucesso!", NovoRankingActivity.this);
            AndroidUtil.setTextStringFromField(R.id.novoRnkCampenonato, AndroidUtil.EDIT_TEXT, "", this);
            AndroidUtil.setTextStringFromField(R.id.novoRnkTime, AndroidUtil.EDIT_TEXT, "", this);
            AndroidUtil.setTextStringFromField(R.id.novoRnkPontos, AndroidUtil.EDIT_TEXT, "", this);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sincronizarRankings(View view) {
        try {
            RankingTask task = new RankingTask();
            task.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void visualizarRankings(View view) {
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private class RankingTask extends AsyncTask<String, Void, List<Ranking>> {

        private ProgressDialog dialog = new ProgressDialog(NovoRankingActivity.this);

        public RankingTask() {
        }

        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected List<Ranking> doInBackground(String... strings) {

            List<Ranking> rankingsParaSync = null;
            List<Ranking> rankingsSyncronizados = new ArrayList<>();

            try {

                RankingDao rankingDao = (new SSMDataBase(NovoRankingActivity.this, SSMDataBase.DAO_RANKING)).getRankingDao();

                rankingsParaSync = rankingDao.getRankingsParaSync();
                if (rankingsParaSync != null) {
                    for (Ranking rank : rankingsParaSync) {
                        ranking = null;
                        ranking = (service.cadastrar(rank).get(0));

                        if (ranking.getIdRanking() != null || ranking.getIdRanking().equals("") == true) {
                            rankingsSyncronizados.add(ranking);
                        }
                    }

                    if (rankingsSyncronizados.size() > 0) {
                        rankingDao.cadastrarRankingSync(rankingsSyncronizados);
                    }
                }

                return rankingsSyncronizados;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(List<Ranking> rankings) {

            dialog.dismiss();
        }
    }
}
