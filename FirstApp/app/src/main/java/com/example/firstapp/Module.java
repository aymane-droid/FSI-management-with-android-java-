package com.example.firstapp;

import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Module {


    private String titre,CIN_P;
    private int env_hor;

    MainActivity_module a = new MainActivity_module();

    TextView i_Nom_Prof;
    Spinner sp;

    public Module() {
    }

    public Module(String titre) {
        this.titre = titre;
    }

    public Module(String titre, int env_hor, String CIN_P) {
        this.titre = titre;
        this.env_hor = env_hor;
        this.CIN_P = CIN_P;
    }

    public int getEnv_hor() {
        return env_hor;
    }

    public void setEnv_hor(int env_hor) {
        this.env_hor = env_hor;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCIN_P() {
        return CIN_P;
    }

    public void setCIN_P(String CIN_P) {
        this.CIN_P = CIN_P;
    }

    public String getvaleur(){
        ArrayList<Professeur> arl1 = new ArrayList<Professeur>();
        CustomAdapter adapter = new CustomAdapter(this.a,R.layout.spinner_item_model1,
                R.id.textView_item_name,
                R.id.textView_item_percent,
                arl1);


        i_Nom_Prof.setText(adapter.getCIN_P(sp.getSelectedItemPosition()));
        return  i_Nom_Prof.toString();
    }
}
