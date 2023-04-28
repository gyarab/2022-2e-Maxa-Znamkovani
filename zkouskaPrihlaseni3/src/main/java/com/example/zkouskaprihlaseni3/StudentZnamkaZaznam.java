package com.example.zkouskaprihlaseni3;

public class StudentZnamkaZaznam {
    String krestni;
    String prijmeni;
    String test;
    int znamka;
    int hodnota;
    int id;
    public StudentZnamkaZaznam(int id, String prijmeni, String krestni, String test, int znamka, int hodnota) {
        this.id = id;
        this.prijmeni = prijmeni;
        this.krestni = krestni;
        this.test = test;
        this.znamka = znamka;
        this.hodnota = hodnota;
    }

    public String getKrestni() {
        return krestni;
    }

    public void setKrestni(String krestni) {
        this.krestni = krestni;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public int getZnamka() {
        return znamka;
    }

    public void setZnamka(int znamka) {
        this.znamka = znamka;
    }

    public int getHodnota() {
        return hodnota;
    }

    public void setHodnota(int hodnota) {
        this.hodnota = hodnota;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
