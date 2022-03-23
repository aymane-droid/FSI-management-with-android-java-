package com.example.firstapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adap_RecyV extends RecyclerView.Adapter<Adap_RecyV.MyHolder> {
    Context context;
    ArrayList<Etudiant> arl;
    Data_gestion db;

    public Adap_RecyV(Context context, ArrayList<Etudiant> arl) {
        this.context = context;
        this.arl = arl;
    }

    public Adap_RecyV(Context context, ArrayList<Etudiant> arl, Data_gestion db) {
        this.context = context;
        this.arl = arl;
        this.db = db;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View item=inflater.inflate(R.layout.item_model,parent,false);

        return new MyHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
       // Association des donnees
        holder.i_CNE.setText(arl.get(position).getCNE());
        holder.i_Nom.setText(arl.get(position).getNom());
        holder.i_Ville.setText(arl.get(position).getVille());

        holder.conteneur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"le Etudiant selectionné est : CNE " +arl.get(position).getCNE()+ " Nom: "
                  //      +arl.get(position).getNom(),Toast.LENGTH_LONG).show();

                db= new Data_gestion(context,"stock.db",null,1);

                String CNE = arl.get(position).getCNE();
                String NOM = arl.get(position).getNom();
                String VILLE = arl.get(position).getVille();

                Dialog dialog = new Dialog(context);

                dialog.setContentView(R.layout.dialog_item);

                EditText d_CNE = dialog.findViewById(R.id.id_CNE);
                EditText d_NOM = dialog.findViewById(R.id.id_NOM);
                EditText d_VILLE = dialog.findViewById(R.id.id_VILLE);

                Button mod = dialog.findViewById(R.id.btn_m);
                Button anuller = dialog.findViewById(R.id.btn_a); //onrestor
                Button supp = dialog.findViewById(R.id.btn_s);

                d_CNE.setText(CNE); // String.ValueOf()
                d_NOM.setText(NOM);
                d_VILLE.setText(VILLE);

                dialog.show();

                mod.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.MAJ_Etu(d_CNE.getText().toString(),d_NOM.getText().toString(),d_VILLE.getText().toString());
                        dialog.dismiss();
                        showAll();
                    }
                });

                anuller.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                supp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.effacer_Etu(CNE);
                        dialog.dismiss();

                        showAll();
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return arl.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView i_CNE,i_Nom,i_Ville;
        LinearLayout conteneur;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            i_Nom=itemView.findViewById(R.id.item_Nom);
            i_Ville=itemView.findViewById(R.id.item_ville);
            i_CNE=itemView.findViewById(R.id.item_CNE);
            conteneur=itemView.findViewById(R.id.conteneur);

        }
    }
    public void showAll(){
        arl.clear();
        arl.addAll(db.afficher_Etu());

        this.notifyDataSetChanged();
    }
}
