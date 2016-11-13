package com.metodista.pap.ssm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.metodista.pap.ssm.model.Usuario;
import com.metodista.pap.ssm.services.UsuarioService;
import com.metodista.pap.ssm.utils.AndroidUtil;

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
            this.usuario = new Usuario();

            this.usuario.setName(AndroidUtil.getTextStringFromField(R.id.contaNome, AndroidUtil.EDIT_TEXT, this));
            this.usuario.setEmail(AndroidUtil.getTextStringFromField(R.id.contaEmail, AndroidUtil.EDIT_TEXT, this));
            this.usuario.setPass(AndroidUtil.getTextStringFromField(R.id.contaSenha, AndroidUtil.EDIT_TEXT, this));
            String tempConfirmaSenha = AndroidUtil.getTextStringFromField(R.id.contaConfirmaSenha, AndroidUtil.EDIT_TEXT, this);

            if (this.usuario.getName().equals("")) {
                AndroidUtil.showShortMessage("Informe o Nome do Usuário da Nova Conta.", this);
                return;
            } else if (this.usuario.getEmail().equals("")) {
                AndroidUtil.showShortMessage("Informe o E-mail do Usuário da Nova Conta.", this);
                return;
            } else if (this.usuario.getPass().equals("")) {
                AndroidUtil.showShortMessage("Informe uma Senha do Usuário da Nova Conta.", this);
                return;
            }

            if (this.usuario.getPass().equals(tempConfirmaSenha) == false) {
                AndroidUtil.showShortMessage("As senhas não conferem. Digite uma senha válida e insira novamente no campo Confirma Senha.", this);
                return;
            }

            NovaContaActivity.UsuarioTask task = new NovaContaActivity.UsuarioTask();
            task.execute();

            startActivity(new Intent(NovaContaActivity.this, MainActivity.class));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelarCadastro(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    private class UsuarioTask extends AsyncTask<String, Void, List<Usuario>> {

        private ProgressDialog dialog = new ProgressDialog(NovaContaActivity.this);

        public UsuarioTask() {
        }

        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected List<Usuario> doInBackground(String... strings) {
            try {
                return service.cadastrarUsuario(usuario);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(List<Usuario> usuarios) {
            super.onPostExecute(usuarios);

            dialog.dismiss();

            AndroidUtil.showShortMessage("Conta cadastrada com sucesso! Efetue o login com o seu e-email cadastrado.", NovaContaActivity.this);
        }
    }
}
