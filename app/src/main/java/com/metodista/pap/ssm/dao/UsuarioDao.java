package com.metodista.pap.ssm.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.metodista.pap.ssm.model.Usuario;

public class UsuarioDao {

    private SQLiteDatabase dbContext;

    public UsuarioDao(SQLiteDatabase dbContext){
        this.dbContext = dbContext;
    }

    public Usuario autenticarUsuario(Usuario usuario) {
        try {
            this.dbContext.execSQL("DELETE FROM Autenticacao;");

            ContentValues values = new ContentValues();
            values.put("idUsuario", usuario.getIdUsuario());
            values.put("nome", usuario.getName());
            values.put("email", usuario.getEmail());
            values.put("senha", usuario.getPass());

            long _id = this.dbContext.insert("Autenticacao", null, values);
            usuario.set_id(_id);

        } finally {
            if (this.dbContext != null) {
                this.dbContext.close();
            }
        }

        return usuario;
    }

    public Usuario consultarUsuario(Long _id) {
        Usuario usuario = null;
        Cursor cursor = null;

        try {
            String[] where = new String[]{String.valueOf(_id)};
            cursor = this.dbContext.rawQuery("SELECT idUsuario, nome, email, senha FROM Autenticacao where _id = ?", where);
            cursor.moveToFirst();

            usuario = new Usuario();
            for (int i = 0; i < cursor.getCount(); i++) {
                usuario.set_id(_id);
                usuario.setIdUsuario(cursor.getString(0));
                usuario.setName(cursor.getString(1));
                usuario.setEmail(cursor.getString(2));
                usuario.setPass(cursor.getString(3));
                cursor.moveToNext();
            }

        } finally {

            if (cursor != null) {
                cursor.close();
            }

            if (this.dbContext != null) {
                this.dbContext.close();
            }
        }

        return usuario;
    }
}
