package com.metodista.pap.ssm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.metodista.pap.ssm.dao.SSMDataBase;
import com.metodista.pap.ssm.dao.TemporadaDao;
import com.metodista.pap.ssm.model.Temporada;
import com.metodista.pap.ssm.utils.AndroidUtil;

public class EntrarTemporadaActivity extends AppCompatActivity {

    private Temporada temporada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar_temporada);
    }

    public void entrarNumaTemporada(View view) {
        try {
            String nomeDigitadoDaTemporada = AndroidUtil.getTextStringFromField(R.id.entrarTempNome, AndroidUtil.EDIT_TEXT, this);
            if (nomeDigitadoDaTemporada.equals("")) {
                AndroidUtil.showShortMessage("Digite o nome da Temporada", this);
                return;
            }

            TemporadaDao temporadaDao = (new SSMDataBase(EntrarTemporadaActivity.this, SSMDataBase.DAO_TEMPORADAS)).getTemporadaDao();
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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
