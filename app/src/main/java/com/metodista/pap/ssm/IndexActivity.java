package com.metodista.pap.ssm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.metodista.pap.ssm.dao.SSMDataBase;
import com.metodista.pap.ssm.dao.UsuarioDao;
import com.metodista.pap.ssm.model.Usuario;
import com.metodista.pap.ssm.services.ConfiguracoesSSM;
import com.metodista.pap.ssm.utils.AndroidUtil;

public class IndexActivity extends AppCompatActivity {

    private SSMDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        if (ConfiguracoesSSM.getInstance().getUsuarioLogado() != null) {
            UsuarioDao usuarioDao = (new SSMDataBase(this, SSMDataBase.DAO_USUARIOS)).getUsuarioDao();
            Usuario usuario = usuarioDao.consultarUsuario(ConfiguracoesSSM.getInstance().getUsuarioLogado().getId());

            String texto = ((TextView) findViewById(R.id.indexUser)).getText().toString();
            ((TextView) findViewById(R.id.indexUser)).setText(texto + " " + usuario.getName());
        } else {
            AndroidUtil.showShortMessage("Usuário não autenticado!", this);
            startActivity(new Intent(IndexActivity.this, MainActivity.class));
        }
    }

    public void criarNovaTemporada(View view){
        startActivity(new Intent(this, NovaTemporadaActivity.class));
    }

    public void encerrar(View view){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
