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
            this.usuario = new Usuario();

            this.usuario.setName(AndroidUtil.getTextStringFromField(R.id.contaNome, AndroidUtil.EDIT_TEXT, this));
            this.usuario.setEmail(AndroidUtil.getTextStringFromField(R.id.contaEmail, AndroidUtil.EDIT_TEXT, this));
            this.usuario.setPass(AndroidUtil.getTextStringFromField(R.id.contaSenha, AndroidUtil.EDIT_TEXT, this));
            String tempConfirmaSenha = AndroidUtil.getTextStringFromField(R.id.contaConfirmaSenha, AndroidUtil.EDIT_TEXT, this);

            if (this.usuario.getName().equals("")) {
                AndroidUtil.showShortMessage("Informe o Nome do Usuário da Nova Conta.", this);
            }else if (this.usuario.getEmail().equals("")) {
                AndroidUtil.showShortMessage("Informe o E-mail do Usuário da Nova Conta.", this);
            }else if (this.usuario.getPass().equals("")) {
                AndroidUtil.showShortMessage("Informe uma Senha do Usuário da Nova Conta.", this);
            }

            if (this.usuario.getPass().equals(tempConfirmaSenha) == false){
                AndroidUtil.showShortMessage("As senhas não conferem. Digite uma senha válida e insira novamente no campo Confirma Senha.", this);
            }

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
