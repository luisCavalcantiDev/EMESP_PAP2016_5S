package com.metodista.pap.ssm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.metodista.pap.ssm.dao.SSMDataBase;
import com.metodista.pap.ssm.dao.TemporadaDao;
import com.metodista.pap.ssm.model.Temporada;
import com.metodista.pap.ssm.services.TemporadaService;
import com.metodista.pap.ssm.utils.AndroidUtil;

import java.util.List;

public class NovaTemporadaActivity extends AppCompatActivity {

    private TemporadaService service = new TemporadaService();
    private Temporada temporada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_temporada);
    }

    public void cadastrar(View view) {
        try {
            this.temporada = new Temporada();

            this.temporada.setName(AndroidUtil.getTextStringFromField(R.id.temporadaNome, AndroidUtil.EDIT_TEXT, this));

            if (this.temporada.getName().equals("")) {
                AndroidUtil.showShortMessage("Informe o Nome da Nova Temporada.", this);
                return;
            }

            NovaTemporadaActivity.TemporadaTask task = new TemporadaTask();
            task.execute();

            startActivity(new Intent(NovaTemporadaActivity.this, IndexActivity.class));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private class TemporadaTask extends AsyncTask<String, Void, List<Temporada>> {

        private ProgressDialog dialog = new ProgressDialog(NovaTemporadaActivity.this);

        public TemporadaTask() {
        }

        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected List<Temporada> doInBackground(String... strings) {
            try{
                return service.cadastrar(temporada);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(List<Temporada> temporadas) {
            super.onPostExecute(temporadas);

            if(temporadas != null){

                TemporadaDao temporadaDao = (new SSMDataBase(NovaTemporadaActivity.this, SSMDataBase.DAO_TEMPORADAS)).getTemporadaDao();
                temporadaDao.cadastrarTemporada(temporadas.get(0));

                AndroidUtil.showShortMessage("Temporada cadastrada com sucesso!", NovaTemporadaActivity.this);
            }

            dialog.dismiss();
        }
    }
}
