package com.sandra.adocaors.model;

public class DataClass {

    private String dataLocal;
    private String dataDesc;
    private String dataName;
    private int dataImage;

    public String getDataLocal() {
        return dataLocal;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataName() {
        return dataName;
    }

    public int getDataImage() {
        return dataImage;
    }

    public DataClass(String dataLocal, String dataDesc, String dataName, int dataImage) {
        this.dataLocal = dataLocal;
        this.dataDesc = dataDesc;
        this.dataName = dataName;
        this.dataImage = dataImage;
    }
}