package com.example.zkouskaprihlaseni3;

public class PrumernaZnamka {
    String prijmeni;
    String krestni;
    double znamkaPrumer;

    public PrumernaZnamka(String prijmeni, String krestni, double znamkaPrumer) {
        this.krestni = krestni;
        this.prijmeni = prijmeni;
        this.znamkaPrumer = znamkaPrumer;
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

    public double getZnamkaPrumer() {
        return znamkaPrumer;
    }

    public void setZnamkaPrumer(double znamkaPrumer) {
        this.znamkaPrumer = znamkaPrumer;
    }
}
