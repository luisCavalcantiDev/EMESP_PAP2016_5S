package com.metodista.pap.ssm.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.metodista.pap.ssm.model.Temporada;
import com.metodista.pap.ssm.services.ConfiguracoesSSM;

public class TemporadaDao {

    private SQLiteDatabase dbContext;

    public TemporadaDao(SQLiteDatabase dbContext){
        this.dbContext = dbContext;
    }

    public Temporada cadastrarTemporada(Temporada temporada){
        try{
            ContentValues values = new ContentValues();
            values.put("nome", temporada.getName());
            values.put("idTemporada", temporada.getId());
            values.put("adminID", ConfiguracoesSSM.getInstance().getUsuarioLogado().getIdUsuario());

            if(temporada.getIdTemporada() == null){
                long id = this.dbContext.insert("Temporadas", null, values);
                temporada.setIdTemporada(id);
            }else{
                String[] where = new String[]{String.valueOf(temporada.getIdTemporada())};
                dbContext.update("Temporadas", values, "id = ?", where);
            }

        }finally {
            if (this.dbContext != null) {
                this.dbContext.close();
            }
        }

        return temporada;
    }
}
