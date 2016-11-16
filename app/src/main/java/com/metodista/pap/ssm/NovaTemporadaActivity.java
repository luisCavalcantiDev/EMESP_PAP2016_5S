package com.metodista.pap.ssm;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.metodista.pap.ssm.adapters.TemporadaArrayAdapter;
import com.metodista.pap.ssm.dao.SSMDataBase;
import com.metodista.pap.ssm.dao.TemporadaDao;
import com.metodista.pap.ssm.model.Temporada;
import com.metodista.pap.ssm.services.ConfiguracoesSSM;
import com.metodista.pap.ssm.services.TemporadaService;
import com.metodista.pap.ssm.utils.AndroidUtil;

import java.util.ArrayList;
import java.util.List;

public class NovaTemporadaActivity extends AppCompatActivity {

    private TemporadaService service = new TemporadaService();
    private TemporadaArrayAdapter adapter;
    private Temporada temporada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_temporada);

        this.carregarDados();
    }

    public void cadastrar(View view) {
        try {
            this.temporada = new Temporada();
            this.temporada.setName(AndroidUtil.getTextStringFromField(R.id.temporadaNome, AndroidUtil.EDIT_TEXT, this));

            if (this.temporada.getName().equals("")) {
                AndroidUtil.showShortMessage("Informe o Nome da Nova Temporada.", this);
                return;
            }

            this.temporada.setAdminID(ConfiguracoesSSM.getInstance().getUsuarioLogado().getIdUsuario());

            TemporadaDao temporadaDao = (new SSMDataBase(NovaTemporadaActivity.this, SSMDataBase.DAO_TEMPORADAS)).getTemporadaDao();
            temporadaDao.cadastrarTemporada(this.temporada);

            AndroidUtil.showShortMessage("Temporada cadastrada com sucesso!", NovaTemporadaActivity.this);
            AndroidUtil.setTextStringFromField(R.id.temporadaNome, AndroidUtil.EDIT_TEXT, "", this);

            this.carregarDados();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void syncronizar(View view) {
        try {
            TemporadaTask task = new TemporadaTask();
            task.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void carregarDados() {
        TemporadaDao temporadaDao = (new SSMDataBase(NovaTemporadaActivity.this, SSMDataBase.DAO_TEMPORADAS)).getTemporadaDao();
        List<Temporada> temporadas = temporadaDao.getTemporadas();
        if (temporadas != null) {
            adapter = new TemporadaArrayAdapter(getBaseContext(), 0, temporadaDao.getTemporadas());
            ((ListView) findViewById(R.id.temporadaList)).setAdapter(adapter);
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

            List<Temporada> temporadasParaSync = null;
            List<Temporada> temporadasSyncronizadas = new ArrayList<>();

            try {

                TemporadaDao temporadaDao = (new SSMDataBase(NovaTemporadaActivity.this, SSMDataBase.DAO_TEMPORADAS)).getTemporadaDao();

                temporadasParaSync = temporadaDao.getTemporadas();
                if (temporadasParaSync != null) {

                    for (Temporada temp : temporadasParaSync) {

                        temporada = null;
                        temporada = (service.cadastrar(temp).get(0));

                        if (temporada.getIdTemporada() != null || temporada.getIdTemporada().equals("") == true) {
                            temporadasSyncronizadas.add(temporada);
                        }
                    }

                    if(temporadasSyncronizadas.size() > 0){
                        temporadaDao.cadastrarTemporadasSync(temporadasSyncronizadas);
                    }
                }

                return temporadasSyncronizadas;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(List<Temporada> temporadas) {
            if (temporadas != null) {
                adapter = new TemporadaArrayAdapter(getBaseContext(), 0, temporadas);
                ((ListView) findViewById(R.id.temporadaList)).setAdapter(adapter);

                AndroidUtil.showShortMessage("Temporadas sincronizadas com sucesso!", NovaTemporadaActivity.this);
            }

            dialog.dismiss();
        }
    }
}
