package com.example.firstapp;

public class Etudiant {

    private String CNE,nom,ville;

    public Etudiant() {
    }

    public Etudiant(String CNE, String nom) {
        this.CNE = CNE;
        this.nom = nom;
    }

    public Etudiant(String CNE, String nom, String ville) {
        this.CNE = CNE;
        this.nom = nom;
        this.ville = ville;
    }

    public String getCNE() {
        return CNE;
    }

    public void setCNE(String CNE) {
        this.CNE = CNE;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
