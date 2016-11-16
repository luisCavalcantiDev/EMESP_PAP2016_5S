package com.metodista.pap.ssm.dao;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.N)
public class SSMDataBase extends SQLiteOpenHelper {

    public static final int INIT_DAO = 0;
    public static final int DAO_USUARIOS = 1;
    public static final int DAO_TEMPORADAS = 2;

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

    public void init(){

    }

    public UsuarioDao getUsuarioDao() {
        if (this.daoInstance == DAO_USUARIOS) {
            usuarioDao = new UsuarioDao(this.getWritableDatabase());
        }

        return usuarioDao;
    }

    public TemporadaDao getTemporadaDao() {
        if (this.daoInstance == DAO_TEMPORADAS) {
            temporadaDao = new TemporadaDao(this.getWritableDatabase());
        }

        return temporadaDao;
    }

    private void setup(SQLiteDatabase db) {
        Log.d("SSMDataBase", "onCreate");

        //Autenticacao do Usuário
        db.execSQL("CREATE TABLE IF NOT EXISTS Autenticacao (_id integer primary key autoincrement, nome text, email text, senha text, idUsuario integer);");

        //Temporadas do Usuário e Compartilhadas
        db.execSQL("CREATE TABLE IF NOT EXISTS Temporadas (_id integer primary key autoincrement, nome text, adminID text, idTemporada integer);");

        //Ranking do Usuário e Compartilhados
        db.execSQL("CREATE TABLE IF NOT EXISTS Ranking (_id integer primary key autoincrement, usuario integer, temporada integer, campeonato text, time text, pontos REAL, idRanking integer);");
    }
}
