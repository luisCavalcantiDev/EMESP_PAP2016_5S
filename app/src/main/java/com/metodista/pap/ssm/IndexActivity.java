package com.metodista.pap.ssm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.metodista.pap.ssm.dao.SSMDataBase;
import com.metodista.pap.ssm.dao.UsuarioDao;
import com.metodista.pap.ssm.model.Temporada;
import com.metodista.pap.ssm.model.Usuario;
import com.metodista.pap.ssm.services.ConfiguracoesSSM;
import com.metodista.pap.ssm.utils.AndroidUtil;

public class IndexActivity extends AppCompatActivity {

    private SSMDataBase db;
    private Temporada temporadaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        if (ConfiguracoesSSM.getInstance().getUsuarioLogado() != null) {
            UsuarioDao usuarioDao = (new SSMDataBase(this, SSMDataBase.DAO_USUARIOS)).getUsuarioDao();
            Usuario usuario = usuarioDao.consultarUsuario(ConfiguracoesSSM.getInstance().getUsuarioLogado().get_id());

            String texto = ((TextView) findViewById(R.id.indexUser)).getText().toString();
            ((TextView) findViewById(R.id.indexUser)).setText(texto + " " + usuario.getName());
        } else {
            AndroidUtil.showShortMessage("Usuário não autenticado!", this);
            startActivity(new Intent(IndexActivity.this, MainActivity.class));
        }
    }

    public void criarNovaTemporada(View view) {
        startActivity(new Intent(this, NovaTemporadaActivity.class));
    }

    public void selecionarTemporadas(View view) {
        Intent intent = new Intent(this, EntrarTemporadaActivity.class);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK == resultCode) {
            this.temporadaSelecionada = new Temporada();
            this.temporadaSelecionada.setName(data.getStringExtra("temporada_nome"));
            this.temporadaSelecionada.setIdTemporada(data.getStringExtra("temporada_idTemporada"));

            if (this.temporadaSelecionada.getName().equals("") == false && this.temporadaSelecionada.getIdTemporada().equals("") == false) {

                
            }
        }
    }

    public void encerrar(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
