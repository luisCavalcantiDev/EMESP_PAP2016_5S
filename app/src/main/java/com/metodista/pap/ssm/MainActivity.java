package com.metodista.pap.ssm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.metodista.pap.ssm.model.Usuario;
import com.metodista.pap.ssm.services.UsuarioService;
import com.metodista.pap.ssm.utils.AndroidUtil;

import java.io.IOException;
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

            String name = ((EditText) findViewById(R.id.mainLogin)).getText().toString();
            String pass = ((EditText) findViewById(R.id.mainSenha)).getText().toString();

            if (name.equals("")) {
                (Toast.makeText(this, "Informe o login (e-mail cadastrado).", Toast.LENGTH_SHORT)).show();
                return;
            }

            if (pass.equals("")) {
                (Toast.makeText(this, "Informe a senha.", Toast.LENGTH_SHORT)).show();
                return;
            }

            this.usuario = new Usuario(name, pass);
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
            dialog.dismiss();
        }
    }
}
