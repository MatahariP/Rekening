package com.example.rekening.model;

import java.io.Serializable;

public class Rekening implements Serializable {
    private int id;
    private String name, rekening;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRekening() {
        return rekening;
    }
    public void setRekening(String rekening) {
        this.rekening = rekening;
    }
}
