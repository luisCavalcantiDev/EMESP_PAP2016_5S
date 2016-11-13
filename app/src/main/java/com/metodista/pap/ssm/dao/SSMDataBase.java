package com.metodista.pap.ssm.dao;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.metodista.pap.ssm.model.Usuario;

@TargetApi(Build.VERSION_CODES.N)
public class SSMDataBase extends SQLiteOpenHelper {

    public SSMDataBase(Context context) {
        super(context, "SSM", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("SSMDataBase", "onCreate");
        db.execSQL("CREATE TABLE Autenticacao (id integer primary key autoincrement, idUsuario integer, nome text, email text, senha text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Usuario autenticarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("idUsuario", usuario.getId());
            values.put("nome", usuario.getName());
            values.put("email", usuario.getEmail());
            values.put("senha", usuario.getPass());

            if (usuario.getIdAutenticacao() == null) {
                long id = db.insert("Autenticacao", null, values);
                usuario.setIdAutenticacao(id);

            } else {
                db.update("Autenticacao", values, "", null);
            }

        } finally {
            if (db != null) {
                db.close();
            }
        }

        return usuario;
    }

    public Usuario consultarUsuario(String idUsuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        Usuario usuario = null;
        Cursor cursor = null;

        try {
            String[] where = new String[]{String.valueOf(idUsuario)};
            cursor = db.rawQuery("SELECT id, nome, email, senha FROM Autenticacao where idUsuario = ?", where);
            cursor.moveToFirst();

            usuario = new Usuario();
            for (int i = 0; i < cursor.getCount(); i++) {
                usuario.setId(idUsuario);
                usuario.setIdAutenticacao(cursor.getLong(0));
                usuario.setName(cursor.getString(1));
                usuario.setEmail(cursor.getString(2));
                usuario.setPass(cursor.getString(3));
                cursor.moveToNext();
            }

        } finally {

            if (cursor != null) {
                cursor.close();
            }

            if (db != null) {
                db.close();
            }
        }

        return usuario;
    }
}
