package com.metodista.pap.ssm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.metodista.pap.ssm.dao.SSMDataBase;
import com.metodista.pap.ssm.dao.UsuarioDao;
import com.metodista.pap.ssm.model.Usuario;
import com.metodista.pap.ssm.services.ConfiguracoesSSM;
import com.metodista.pap.ssm.services.UsuarioService;
import com.metodista.pap.ssm.utils.AndroidUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UsuarioService service = new UsuarioService();
    private Usuario usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void criarNovaConta(View view) {
        startActivity(new Intent(this, NovaContaActivity.class));
    }

    public void enviar(View view) {

        try {

            String email = AndroidUtil.getTextStringFromField(R.id.mainLogin, AndroidUtil.EDIT_TEXT, this);
            String pass = AndroidUtil.getTextStringFromField(R.id.mainSenha, AndroidUtil.EDIT_TEXT, this);

            if (email.equals("")) {
                AndroidUtil.showShortMessage("Informe o login (e-mail cadastrado", this);
                return;
            }

            if (pass.equals("")) {
                AndroidUtil.showShortMessage("Informe a senha.", this);
                return;
            }

            this.usuario = new Usuario(email, pass);
            UsuarioTask task = new UsuarioTask();
            task.execute();

            startActivity(new Intent(MainActivity.this, IndexActivity.class));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private class UsuarioTask extends AsyncTask<String, Void, List<Usuario>> {

        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        public UsuarioTask() {
        }

        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected List<Usuario> doInBackground(String... params) {
            try {
                return service.autenticar(usuario);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(List<Usuario> usuarios) {
            super.onPostExecute(usuarios);

            if (usuarios != null) {

                UsuarioDao usuarioDao = (new SSMDataBase(MainActivity.this, SSMDataBase.DAO_USUARIOS)).getUsuarioDao();
                ConfiguracoesSSM.getInstance().setUsuarioLogado(usuarioDao.autenticarUsuario(usuarios.get(0)));
            }

            dialog.dismiss();
        }
    }
}
