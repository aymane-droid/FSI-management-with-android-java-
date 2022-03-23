package com.example.firstapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity_module extends AppCompatActivity {

    EditText titre,env_h;
    Button badd,bTitre,bNom,benv_h,bsup;
    Spinner sp;
    RecyclerView recycleView;
    Adap_RecyV_module adap_recycl;
    Data_gestion db;
    CustomAdapter adapter;
    ArrayList<Module> arl2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        titre = findViewById(R.id.id_titre);
        env_h = findViewById(R.id.id_env);
        sp = findViewById(R.id.spinner);
        recycleView=findViewById(R.id.lvg);
        db=new Data_gestion(this,"stock.db",null,1);

        badd = findViewById(R.id.id_add);
        bTitre = findViewById(R.id.bt_titre);
        bNom = findViewById(R.id.bt_prof);
        benv_h = findViewById(R.id.bt_env);
        bsup=findViewById(R.id.id_sup);

        arl2=new ArrayList<Module>();
        adap_recycl=new Adap_RecyV_module(this,arl2);

        recycleView.setAdapter(adap_recycl);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Professeur> arl = new ArrayList<Professeur>();

        arl.addAll(db.afficher_Nom_CIN_1());

        //ArrayAdapter<Professeur> adapter = new ArrayAdapter<Professeur>(this, android.R.layout.simple_spinner_dropdown_item,arl);

        adapter = new CustomAdapter(MainActivity_module.this,
                R.layout.spinner_item_model1,
                R.id.textView_item_name,
                R.id.textView_item_percent,
                arl);

        this.sp.setAdapter(adapter);

        showAll();

        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Context con;
                Toast.makeText(MainActivity_module.this,"titre  "+titre.getText().toString()+"  env  "+Integer.parseInt(env_h.getText().toString())+" "+adapter.getCIN_P(sp.getSelectedItemPosition()),Toast.LENGTH_LONG).show();
                //Toast.makeText(con,"",Toast.LENGTH_LONG).show();
                db.inserer_Mod(titre.getText().toString(),Integer.parseInt(env_h.getText().toString()),adapter.getCIN_P(sp.getSelectedItemPosition()));
                vider();
                showAll();
            }
        });

        bTitre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl2.clear();
                arl2.addAll(db.recherche_NOM_Module(titre.getText().toString()));

                adap_recycl.notifyDataSetChanged();
            }
        });

        bNom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl2.clear();
                arl2.addAll(db.recherche_NOM_Prof_mod(adapter.getCIN_P(sp.getSelectedItemPosition())));

                adap_recycl.notifyDataSetChanged();
            }
        });

        benv_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl2.clear();
                arl2.addAll(db.recherche_ENV_HOR(Integer.parseInt(env_h.getText().toString())));

                adap_recycl.notifyDataSetChanged();
            }
        });

        bsup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.effacer_mod(titre.getText().toString());
                vider();
                showAll();
            }
        });

    }
    public void showAll(){
        arl2.clear();
        arl2.addAll(db.afficher_Module());

        adap_recycl.notifyDataSetChanged();
    }

    public void vider(){
        titre.setText("");
        sp.setAdapter(adapter);
        env_h.setText("");
    }

}
