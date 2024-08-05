package com.sandra.adocaors.model.database;

import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractDAO {

    protected SQLiteDatabase db;
    protected DBOpenHelper helper;

    protected final void Open(){
        db = helper.getWritableDatabase();
    }

    protected final void Close() {
        helper.close();
    }
}
