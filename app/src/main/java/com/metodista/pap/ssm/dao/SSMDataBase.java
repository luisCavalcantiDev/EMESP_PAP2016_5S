package com.metodista.pap.ssm.dao;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.N)
public class SSMDataBase extends SQLiteOpenHelper {

    public static final int DAO_USUARIOS = 1;
    public static final int DAO_TEMPORADAS = 1;

    private int daoInstance;
    private UsuarioDao usuarioDao = null;
    private TemporadaDao temporadaDao = null;

    public SSMDataBase(Context context, int dao) {
        super(context, "SSM", null, 1);
        this.daoInstance = dao;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.setup(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        this.setup(db);
    }

    public UsuarioDao getUsuarioDao() {
        if(this.daoInstance == DAO_USUARIOS){
            usuarioDao = new UsuarioDao(this.getWritableDatabase());
        }

        return usuarioDao;
    }

    public TemporadaDao getTemporadaDao() {
        if(this.daoInstance == DAO_USUARIOS){
            temporadaDao = new TemporadaDao(this.getWritableDatabase());
        }

        return temporadaDao;
    }

    private void setup(SQLiteDatabase db){
        Log.d("SSMDataBase", "onCreate");

        //Autenticacao
        db.execSQL("CREATE TABLE IF NOT EXISTS Autenticacao (id long primary key autoincrement, idUsuario integer, nome text, email text, senha text);");

        //Temporadas do Usuário
        db.execSQL("CREATE TABLE IF NOT EXISTS Temporadas (id long primary key autoincrement, idTemporada integer, nome text, adminID integer);");
    }
}
