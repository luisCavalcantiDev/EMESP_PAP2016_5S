package com.metodista.pap.ssm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.metodista.pap.ssm.model.Temporada;

public class NovoRankingActivity extends AppCompatActivity {

    private Temporada temporada = null;

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

    public void cadastrarRankings(View view) {
        try {
            /*
            String nomeDigitadoDaTemporada = AndroidUtil.getTextStringFromField(R.id.entrarTempNome, AndroidUtil.EDIT_TEXT, this);
            if (nomeDigitadoDaTemporada.equals("")) {
                AndroidUtil.showShortMessage("Digite o nome da Temporada", this);
                return;
            }

            TemporadaDao temporadaDao = (new SSMDataBase(NovoRankingActivity.this, SSMDataBase.DAO_TEMPORADAS)).getTemporadaDao();
            this.temporada = temporadaDao.getTemporadaPorNome(nomeDigitadoDaTemporada);
            if (this.temporada != null) {
                AndroidUtil.setTextStringFromField(R.id.temporadaNome, AndroidUtil.EDIT_TEXT, "", this);

                Intent intent = new Intent();
                intent.putExtra("temporada_nome", temporada.getName());
                intent.putExtra("temporada_idTemporada", temporada.getIdTemporada());
                setResult(Activity.RESULT_OK, intent);
                finish();
            } else {
                AndroidUtil.showShortMessage("Não existe uma Temporada com o nome [" + nomeDigitadoDaTemporada + "] compartilhada para Ranking entre os usuários.", this);
                return;
            }
            */

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sincronizarRanking(View view) {
        try {
            /*
            String nomeDigitadoDaTemporada = AndroidUtil.getTextStringFromField(R.id.entrarTempNome, AndroidUtil.EDIT_TEXT, this);
            if (nomeDigitadoDaTemporada.equals("")) {
                AndroidUtil.showShortMessage("Digite o nome da Temporada", this);
                return;
            }

            TemporadaDao temporadaDao = (new SSMDataBase(NovoRankingActivity.this, SSMDataBase.DAO_TEMPORADAS)).getTemporadaDao();
            this.temporada = temporadaDao.getTemporadaPorNome(nomeDigitadoDaTemporada);
            if (this.temporada != null) {
                AndroidUtil.setTextStringFromField(R.id.temporadaNome, AndroidUtil.EDIT_TEXT, "", this);

                Intent intent = new Intent();
                intent.putExtra("temporada_nome", temporada.getName());
                intent.putExtra("temporada_idTemporada", temporada.getIdTemporada());
                setResult(Activity.RESULT_OK, intent);
                finish();
            } else {
                AndroidUtil.showShortMessage("Não existe uma Temporada com o nome [" + nomeDigitadoDaTemporada + "] compartilhada para Ranking entre os usuários.", this);
                return;
            }
            */

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
