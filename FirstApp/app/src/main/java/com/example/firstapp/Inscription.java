package com.example.firstapp;

public class Inscription {

    private String CNE,titre,date;

    public Inscription() {
    }

    public Inscription(String CNE, String titre, String date) {
        this.CNE = CNE;
        this.titre = titre;
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCNE() {
        return CNE;
    }

    public void setCNE(String CNE) {
        this.CNE = CNE;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
