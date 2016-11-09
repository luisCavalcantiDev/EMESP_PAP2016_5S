package com.metodista.pap.ssm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.metodista.pap.ssm.model.Usuario;
import com.metodista.pap.ssm.services.UsuarioService;
import com.metodista.pap.ssm.utils.AndroidUtil;

import java.io.IOException;
import java.util.List;

public class NovaContaActivity extends AppCompatActivity {

    private UsuarioService service = new UsuarioService();
    private Usuario usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_conta);
    }

    public void cadastrar(View view) {

        try {
            Usuario novoUsuario = new Usuario();

            novoUsuario.setName(AndroidUtil.getTextStringFromField(R.id.contaNome, AndroidUtil.EDIT_TEXT, this));
            novoUsuario.setEmail(AndroidUtil.getTextStringFromField(R.id.contaEmail, AndroidUtil.EDIT_TEXT, this));
            novoUsuario.setPass(AndroidUtil.getTextStringFromField(R.id.contaSenha, AndroidUtil.EDIT_TEXT, this));
            String tempConfirmaSenha = AndroidUtil.getTextStringFromField(R.id.contaConfirmaSenha, AndroidUtil.EDIT_TEXT, this);

            if (novoUsuario.getName().equals("")) {
                AndroidUtil.showShortMessage("Informe o Nome do Usuário da Nova Conta.", this);
            }else if (novoUsuario.getEmail().equals("")) {
                AndroidUtil.showShortMessage("Informe o E-mail do Usuário da Nova Conta.", this);
            }else if (novoUsuario.getPass().equals("")) {
                AndroidUtil.showShortMessage("Informe uma Senha do Usuário da Nova Conta.", this);
            }

            if (novoUsuario.getPass().equals(tempConfirmaSenha) == false){
                AndroidUtil.showShortMessage("As senhas não conferem. Digite uma senha válida e insira novamente no campo Confirma Senha.", this);
            }

            this.usuario = new Usuario();
            NovaContaActivity.UsuarioTask task = new NovaContaActivity.UsuarioTask();
            task.execute();

            startActivity(new Intent(NovaContaActivity.this, IndexActivity.class));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private class UsuarioTask extends AsyncTask<String, Void, Boolean> {

        private ProgressDialog dialog = new ProgressDialog(NovaContaActivity.this);

        public UsuarioTask() {
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                return service.postUsuario(usuario);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            dialog.dismiss();
        }
    }
}
