package com.metodista.pap.ssm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.metodista.pap.ssm.proxy.UsuarioProxy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void criarNovaConta(View view){
        startActivity(new Intent(this, NovaContaActivity.class));
    }

    public void enviar(View view){
        //TODO: receber autorização do WS para entrar na apk

        //Teste de comunicação com API no NodeJS

        UsuarioProxy proxy = new UsuarioProxy();
        proxy.execute();

        startActivity(new Intent(this,IndexActivity.class));
    }
}
