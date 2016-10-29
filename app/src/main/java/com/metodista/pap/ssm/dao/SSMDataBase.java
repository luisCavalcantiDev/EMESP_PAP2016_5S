package com.metodista.pap.ssm.dao;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.N)
public class SSMDataBase extends SQLiteOpenHelper {

    public SSMDataBase(Context context) {
        super(context, "SSM", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Usuarios (id integer primary key autoincrement, nome text, email text, senha text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
