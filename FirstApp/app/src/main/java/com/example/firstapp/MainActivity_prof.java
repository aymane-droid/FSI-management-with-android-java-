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

public class MainActivity_prof extends AppCompatActivity {
    EditText CIN,nom,grad;
    Button badd,bCIN,bNom,bGrad;
    RecyclerView recycleView;
    ArrayList<Professeur> arl;
    Adap_RecyV_Prof adap_recycl;
    Data_gestion database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);

        CIN=findViewById(R.id.z1);
        nom=findViewById(R.id.z2);
        grad=findViewById(R.id.z3);
        recycleView=findViewById(R.id.lvg);
        database=new Data_gestion(this,"stock.db",null,1);

        badd = findViewById(R.id.bt_add);
        bCIN = findViewById(R.id.bt_cne);
        bNom = findViewById(R.id.bt_nom);
        bGrad = findViewById(R.id.bt_ville);

        arl=new ArrayList<Professeur>();
        adap_recycl=new Adap_RecyV_Prof(this,arl);

        recycleView.setAdapter(adap_recycl);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        showAll();


        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.inserer_Prof(CIN.getText().toString(),nom.getText().toString(),grad.getText().toString());
                vider();
                showAll();
                //database.effacer();
            }
        });

        bCIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.clear();
                arl.addAll(database.recherche_CIN_Prof(CIN.getText().toString()));
                adap_recycl.notifyDataSetChanged();
            }
        });

        bNom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.clear();
                arl.addAll(database.recherche_NOM_Prof(nom.getText().toString()));
                adap_recycl.notifyDataSetChanged();
            }
        });

        bGrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.clear();
                arl.addAll(database.recherche_GRAD_Prof(grad.getText().toString()));
                adap_recycl.notifyDataSetChanged();
            }
        });

        //recycleView.setOnClickListener();


    }
    public void showAll(){
        arl.clear();
        arl.addAll(database.afficher_Prof());

        adap_recycl.notifyDataSetChanged();
    }

    public void vider(){
        CIN.setText("");
        nom.setText("");
        grad.setText("");
    }
}
