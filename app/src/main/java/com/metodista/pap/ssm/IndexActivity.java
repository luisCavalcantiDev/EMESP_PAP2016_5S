package com.metodista.pap.ssm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.metodista.pap.ssm.dao.SSMDataBase;
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
            db = new SSMDataBase(this);
            Usuario usuario = db.consultarUsuario(ConfiguracoesSSM.getInstance().getUsuarioLogado().getId());

            String texto = ((TextView) findViewById(R.id.indexUser)).getText().toString();
            ((TextView) findViewById(R.id.indexUser)).setText(texto + " " + usuario.getName());
        } else {
            AndroidUtil.showShortMessage("Usuário não autenticado!", this);
            startActivity(new Intent(IndexActivity.this, MainActivity.class));
        }
    }
}
