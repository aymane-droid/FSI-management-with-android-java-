package com.example.firstapp;

public class Professeur {

    private String CIN,nom,grad;

    public Professeur() {
    }

    public Professeur(String CIN, String nom, String grad) {
        this.CIN = CIN;
        this.nom = nom;
        this.grad = grad;
    }

    public Professeur(String CIN, String nom) {
        this.CIN = CIN;
        this.nom = nom;
    }

    public Professeur(String nom) {
        this.nom = nom;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }
}
