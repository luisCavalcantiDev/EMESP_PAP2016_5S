package com.metodista.pap.ssm.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.metodista.pap.ssm.model.Temporada;

import java.util.ArrayList;
import java.util.List;

public class TemporadaDao {

    private SQLiteDatabase dbContext;

    public TemporadaDao(SQLiteDatabase dbContext) {
        this.dbContext = dbContext;
    }

    public Temporada cadastrarTemporada(Temporada temporada) {
        try {
            ContentValues values = new ContentValues();
            values.put("nome", temporada.getName());
            values.put("adminID", temporada.getAdminID());
            values.put("idTemporada", temporada.getIdTemporada());

            if (temporada.get_id() == null) {
                long id = this.dbContext.insert("Temporadas", null, values);
                temporada.set_id(id);
            } else {
                String[] where = new String[]{String.valueOf(temporada.get_id())};
                dbContext.update("Temporadas", values, "_id = ?", where);
            }

        } finally {
            if (this.dbContext != null) {
                this.dbContext.close();
            }
        }

        return temporada;
    }

    public void cadastrarTemporadasSync(List<Temporada> temporadas) {
        try {

            for (Temporada temp : temporadas) {
                ContentValues values = new ContentValues();
                values.put("nome", temp.getName());
                values.put("adminID", temp.getAdminID());
                values.put("idTemporada", temp.getIdTemporada());

                if (temp.get_id() == null) {
                    long id = this.dbContext.insert("Temporadas", null, values);
                    temp.set_id(id);
                } else {
                    String[] where = new String[]{String.valueOf(temp.get_id())};
                    dbContext.update("Temporadas", values, "_id = ?", where);
                }
            }

            this.dbContext.execSQL("DELETE FROM Temporadas WHERE idTemporada = ''");

        } finally {
            if (this.dbContext != null) {
                this.dbContext.close();
            }
        }
    }

    public Temporada getTemporadaPorNome(String nomeTemporada) {
        Temporada temporada = null;
        Cursor cursor = null;

        try {
            cursor = this.dbContext.rawQuery("SELECT _id, nome, adminID, idTemporada FROM Temporadas WHERE idTemporada <> '' AND nome = '" + nomeTemporada + "'", null);
            if (cursor.getCount() > 0) {
                temporada = new Temporada();

                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    temporada.set_id(cursor.getLong(0));
                    temporada.setName(cursor.getString(1));
                    temporada.setAdminID(cursor.getString(2));
                    temporada.setIdTemporada(cursor.getString(3));

                    cursor.moveToNext();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return temporada;
    }

    public List<Temporada> getTemporadas() {
        List<Temporada> temporadas = null;
        Cursor cursor = null;

        try {
            cursor = this.dbContext.rawQuery("SELECT _id, nome, adminID, idTemporada FROM Temporadas", null);
            if (cursor.getCount() > 0) {
                temporadas = new ArrayList<>();

                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    Temporada temporada = new Temporada();
                    temporada.set_id(cursor.getLong(0));
                    temporada.setName(cursor.getString(1));
                    temporada.setAdminID(cursor.getString(2));
                    temporada.setIdTemporada(cursor.getString(3));

                    temporadas.add(temporada);
                    cursor.moveToNext();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return temporadas;
    }

    public List<Temporada> getTemporadasParaSync() {
        List<Temporada> temporadas = null;
        Cursor cursor = null;

        try {
            cursor = this.dbContext.rawQuery("SELECT _id, nome, adminID, idTemporada FROM Temporadas WHERE idTemporada = ''", null);
            if (cursor.getCount() > 0) {
                temporadas = new ArrayList<>();

                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    Temporada temporada = new Temporada();
                    temporada.set_id(cursor.getLong(0));
                    temporada.setName(cursor.getString(1));
                    temporada.setAdminID(cursor.getString(2));
                    temporada.setIdTemporada(cursor.getString(3));

                    temporadas.add(temporada);
                    cursor.moveToNext();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return temporadas;
    }
}
