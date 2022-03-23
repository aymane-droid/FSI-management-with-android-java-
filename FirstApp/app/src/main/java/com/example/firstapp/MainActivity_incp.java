package com.example.firstapp;

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

public class MainActivity_incp extends AppCompatActivity {

    EditText date;
    Button badd,bTitre,bNom,bdate,bsup;
    Spinner sp_etu,sp_mod;
    RecyclerView recycleView;
    Adapt_RecyV_incp adap_recycl;
    Data_gestion db;
    CustomAdapter_Etu adapter;
    CustomAdapter_Mod adapter_mod;
    ArrayList<Inscription> arl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        date = findViewById(R.id.jour);
        sp_etu = findViewById(R.id.spinner2);
        sp_mod = findViewById(R.id.spinner3);
        recycleView=findViewById(R.id.lvg);
        db=new Data_gestion(this,"stock.db",null,1);

        //db.a();

        badd = findViewById(R.id.button);
        bTitre = findViewById(R.id.button2);
        bNom = findViewById(R.id.button3);
        bdate = findViewById(R.id.button4);
        bsup=findViewById(R.id.id_supp);

        arl=new ArrayList<Inscription>();
        adap_recycl=new Adapt_RecyV_incp(this,arl);

        this.recycleView.setAdapter(adap_recycl);
        this.recycleView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Etudiant> arl1 = new ArrayList<Etudiant>();
        arl1.addAll(db.afficher_Mod_CNE_1());
        adapter = new CustomAdapter_Etu(MainActivity_incp.this,
                R.layout.spinner_item_model1,
                R.id.textView_item_name,
                R.id.textView_item_percent,
                arl1);
        this.sp_etu.setAdapter(adapter);

        ArrayList<Module> arl2 = new ArrayList<Module>();
        arl2.addAll(db.affiche_titre());
        adapter_mod = new CustomAdapter_Mod(MainActivity_incp.this,
                R.layout.spinner_item_model1,
                R.id.textView_item_percent,
                arl2);
        this.sp_mod.setAdapter(adapter_mod);

        tout();

        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity_incp.this,"titre  "+adapter.getCNE(sp_etu.getSelectedItemPosition())+"  env  "+adapter_mod.getTitre(sp_mod.getSelectedItemPosition())+" "+date.getText().toString(),Toast.LENGTH_LONG).show();
                db.insetion_insp(adapter.getCNE(sp_etu.getSelectedItemPosition()),adapter_mod.getTitre(sp_mod.getSelectedItemPosition()),date.getText().toString());
                //db.insetion_insp("cd5788","c++","1234565");
                vider();
                tout();
            }
        });

        bTitre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.clear();
                arl.addAll(db.recherche_NOM_Et(adapter.getCNE(sp_etu.getSelectedItemPosition())));

                adap_recycl.notifyDataSetChanged();
            }
        });

        bNom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.clear();
                arl.addAll(db.recherche_NOM_Mo(adapter_mod.getTitre(sp_mod.getSelectedItemPosition())));
                adap_recycl.notifyDataSetChanged();
            }
        });

        bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.clear();
                arl.addAll(db.recherche_date(date.getText().toString()));

                adap_recycl.notifyDataSetChanged();
            }
        });

        bsup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.effacer_inscription(adapter.getCNE(sp_etu.getSelectedItemPosition()),adapter_mod.getTitre(sp_mod.getSelectedItemPosition()));
                vider();
                tout();
            }
        });
    }

    public void tout(){
        arl.clear();
        arl.addAll(db.afficher_insp());

        adap_recycl.notifyDataSetChanged();
    }

    public void vider(){
        sp_etu.setAdapter(adapter);
        sp_mod.setAdapter(adapter_mod);
        date.setText("");
    }

}
