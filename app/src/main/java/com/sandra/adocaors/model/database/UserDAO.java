package com.sandra.adocaors.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;

public class UserDAO extends AbstractDAO{

    private String[] colunas = new String[] {
            UserModel.COLUNA_ID,
            UserModel.COLUNA_USUARIO,
            UserModel.COLUNA_SENHA,
    };

    public UserDAO(Context context) {
        helper = new DBOpenHelper(context);
    }

    public boolean select(final UserModel userModel) {

        try{
            Open();
            Cursor cursor = db.query
                    (UserModel.TABLE_NAME, colunas,
                    UserModel.COLUNA_USUARIO + " = ? AND " + UserModel.COLUNA_SENHA + " = ?",
                    new String[]{userModel.getUsuario(), userModel.getSenha()},
                    null,
                    null,
                    null
                    );
            cursor.moveToFirst();
            return cursor.getCount() > 0;
        } finally {
            Close();
        }
    }

    public ArrayList<UserModel> selectAll() {
        ArrayList<UserModel> userListLocal;

        try{
            Open();
            userListLocal = new ArrayList<UserModel>();
            Cursor cursor = db.query
                    (UserModel.TABLE_NAME, colunas,
                            null,
                           null,
                            null,
                            null,
                            null
                    );
            cursor.moveToFirst();
            while (cursor.isAfterLast()) {
                UserModel userModel = new UserModel();
                userModel.setId(cursor.getInt(0));
                userModel.setUsuario(cursor.getString(1));
                userModel.setSenha(cursor.getString(2));
                userListLocal.add(userModel);
                cursor.moveToNext();
            }
        } finally {
            Close();
        }
        return userListLocal;
    }

    public long insert (final UserModel userModel) {
        long result = -1;

        try{
            Open();
            ContentValues values = new ContentValues();
            values.put(UserModel.COLUNA_USUARIO, userModel.getUsuario());
            values.put(UserModel.COLUNA_SENHA, userModel.getSenha());

           result =  db.insert(UserModel.TABLE_NAME, null, values);

        }finally {
            Close();
        }
        return result;
    }
}
