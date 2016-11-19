package com.metodista.pap.ssm.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.metodista.pap.ssm.model.Ranking;

import java.util.ArrayList;
import java.util.List;

public class RankingDao {

    private SQLiteDatabase dbContext;

    public RankingDao(SQLiteDatabase dbContext) {
        this.dbContext = dbContext;
    }

    public Ranking cadastrarRanking(Ranking ranking) {
        try {
            ContentValues values = new ContentValues();
            values.put("usuario", ranking.getUsuario());
            values.put("temporada", ranking.getTemporada());
            values.put("campeonato", ranking.getCampeonato());
            values.put("time", ranking.getTime());
            values.put("pontos", ranking.getPontos());
            values.put("idRanking", ranking.getIdRanking());

            if (ranking.get_id() == null) {
                long id = this.dbContext.insert("Ranking", null, values);
                ranking.set_id(id);
            } else {
                String[] where = new String[]{String.valueOf(ranking.get_id())};
                dbContext.update("Ranking", values, "_id = ?", where);
            }

        } finally {
            if (this.dbContext != null) {
                this.dbContext.close();
            }
        }

        return ranking;
    }

    public void cadastrarRankingSync(List<Ranking> rankings) {
        try {

            for (Ranking ranking : rankings) {
                ContentValues values = new ContentValues();
                values.put("usuario", ranking.getUsuario());
                values.put("temporada", ranking.getTemporada());
                values.put("campeonato", ranking.getCampeonato());
                values.put("time", ranking.getTime());
                values.put("pontos", ranking.getPontos());
                values.put("idRanking", ranking.getIdRanking());

                if (ranking.get_id() == null) {
                    long id = this.dbContext.insert("Ranking", null, values);
                    ranking.set_id(id);
                } else {
                    String[] where = new String[]{String.valueOf(ranking.get_id())};
                    dbContext.update("Ranking", values, "_id = ?", where);
                }
            }

            this.dbContext.execSQL("DELETE FROM Ranking WHERE idRanking = ''");

        } finally {
            if (this.dbContext != null) {
                this.dbContext.close();
            }
        }
    }

    public List<Ranking> getRankingsParaSync() {
        List<Ranking> rankings = null;
        Cursor cursor = null;

        try {
            cursor = this.dbContext.rawQuery("SELECT _id, usuario, temporada, campeonato, time, pontos, idRanking FROM Ranking WHERE idRanking = ''", null);
            if (cursor.getCount() > 0) {
                rankings = new ArrayList<>();

                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    Ranking ranking = new Ranking();
                    ranking.set_id(cursor.getLong(0));
                    ranking.setUsuario(cursor.getString(1));
                    ranking.setTemporada(cursor.getString(2));
                    ranking.setCampeonato(cursor.getString(3));
                    ranking.setTime(cursor.getString(4));
                    ranking.setPontos(cursor.getInt(5));
                    ranking.setIdRanking(cursor.getString(6));

                    rankings.add(ranking);
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

        return rankings;
    }

    public List<Ranking> getRankings() {
        List<Ranking> rankings = null;
        Cursor cursor = null;

        try {
            cursor = this.dbContext.rawQuery("SELECT _id, usuario, temporada, campeonato, time, pontos, idRanking FROM Ranking", null);
            if (cursor.getCount() > 0) {
                rankings = new ArrayList<>();

                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    Ranking ranking = new Ranking();
                    ranking.set_id(cursor.getLong(0));
                    ranking.setUsuario(cursor.getString(1));
                    ranking.setTemporada(cursor.getString(2));
                    ranking.setCampeonato(cursor.getString(3));
                    ranking.setTime(cursor.getString(4));
                    ranking.setPontos(cursor.getInt(5));
                    ranking.setIdRanking(cursor.getString(6));

                    rankings.add(ranking);
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

        return rankings;
    }

    public List<Ranking> getRankingsPorTemporada(String idTemporada) {
        List<Ranking> rankings = null;
        Cursor cursor = null;

        try {
            cursor = this.dbContext.rawQuery("SELECT _id, usuario, temporada, campeonato, time, pontos, idRanking FROM Ranking WHERE temporada = '" + idTemporada + "'", null);
            if (cursor.getCount() > 0) {
                rankings = new ArrayList<>();

                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    Ranking ranking = new Ranking();
                    ranking.set_id(cursor.getLong(0));
                    ranking.setUsuario(cursor.getString(1));
                    ranking.setTemporada(cursor.getString(2));
                    ranking.setCampeonato(cursor.getString(3));
                    ranking.setTime(cursor.getString(4));
                    ranking.setPontos(cursor.getInt(5));
                    ranking.setIdRanking(cursor.getString(6));

                    rankings.add(ranking);
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

        return rankings;
    }
}
