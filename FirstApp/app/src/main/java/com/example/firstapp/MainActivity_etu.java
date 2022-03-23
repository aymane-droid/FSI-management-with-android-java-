package com.example.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity_etu extends AppCompatActivity {

    EditText CNE,nom,ville;
    Button badd,bCNE,bNom,bVille;
    RecyclerView recycleView;
    ArrayList<Etudiant> arl;
    Adap_RecyV adap_recycl;
    Data_gestion database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etu);

        CNE=findViewById(R.id.z1);
        nom=findViewById(R.id.z2);
        ville=findViewById(R.id.z3);
        recycleView=findViewById(R.id.lvg);
        database=new Data_gestion(this,"stock.db",null,1);

        badd = findViewById(R.id.bt_add);
        bCNE = findViewById(R.id.bt_cne);
        bNom = findViewById(R.id.bt_nom);
        bVille = findViewById(R.id.bt_ville);

        arl=new ArrayList<Etudiant>();
        adap_recycl=new Adap_RecyV(this,arl);

        recycleView.setAdapter(adap_recycl);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        showAll();


        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.inserer_Etu(CNE.getText().toString(),nom.getText().toString(),ville.getText().toString());
                vider();
                showAll();
                //database.effacer();
            }
        });

        bCNE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.clear();
                arl.addAll(database.recherche_CNE_Etu(CNE.getText().toString()));
                adap_recycl.notifyDataSetChanged();
            }
        });

        bNom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.clear();
                arl.addAll(database.recherche_NOM_Etu(nom.getText().toString()));
                adap_recycl.notifyDataSetChanged();
            }
        });

        bVille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.clear();
                arl.addAll(database.recherche_VILLE_Etu(ville.getText().toString()));
                adap_recycl.notifyDataSetChanged();
            }
        });

        //recycleView.setOnClickListener();


    }
    public void showAll(){
        arl.clear();
        arl.addAll(database.afficher_Etu());

        adap_recycl.notifyDataSetChanged();
    }

    public void vider(){
        CNE.setText("");
        nom.setText("");
        ville.setText("");
    }
}

