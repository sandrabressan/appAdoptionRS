package com.sandra.adocaors.model.database;

public class UserModel {

    public static final String TABLE_NAME = "tb_usuario";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_USUARIO = "usuario";
    public static final String COLUNA_SENHA = "senha";

    public static String
        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                    COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_USUARIO + " TEXT NOT NULL UNIQUE, " +
                    COLUNA_SENHA + " TEXT NOT NULL " +" );";

    public static String
        DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;

    private long id;
    private String usuario;
    private String senha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
