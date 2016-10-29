package com.metodista.pap.ssm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.metodista.pap.ssm.model.Usuario;
import com.metodista.pap.ssm.services.UsuarioService;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UsuarioService service = new UsuarioService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void criarNovaConta(View view) {
        startActivity(new Intent(this, NovaContaActivity.class));
    }

    public void enviar(View view) {

        UsuarioTask task = new UsuarioTask();
        task.execute();

        startActivity(new Intent(this, IndexActivity.class));
    }

    private class UsuarioTask extends AsyncTask<String, Void, List<Usuario>> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.show();
        }

        @Override
        protected List<Usuario> doInBackground(String... strings) {
            try {
                return service.autenticar(strings[0]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
