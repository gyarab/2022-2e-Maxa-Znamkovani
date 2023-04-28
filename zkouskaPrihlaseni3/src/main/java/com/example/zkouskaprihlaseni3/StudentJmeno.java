package com.example.zkouskaprihlaseni3;

public class StudentJmeno {
    String krestni;
    String prijmeni;

    public StudentJmeno(String krestni, String prijmeni) {
        this.krestni = krestni;
        this.prijmeni = prijmeni;
    }

    @Override
    public String toString() {
        return krestni + " " +prijmeni;
    }
}
