package com.example.zkouskaprihlaseni3;

public class Ucitel {
    String username;
    String password;
    String krestni;
    String prijmeni;

    public Ucitel(String krestni, String prijmeni, String username, String password) {
        this.krestni = krestni;
        this.prijmeni = prijmeni;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return krestni + " " + prijmeni;
    }
}
