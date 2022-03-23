package com.example.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapt_RecyV_incp extends RecyclerView.Adapter<Adapt_RecyV_incp.MyHolder>{

    Context context;
    ArrayList<Inscription> arl;
    Data_gestion db;

    public Adapt_RecyV_incp(Context context, ArrayList<Inscription> arl) {
        this.context = context;
        this.arl = arl;
    }

    public Adapt_RecyV_incp(Context context, ArrayList<Inscription> arl, Data_gestion db) {
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
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.i_env.setText(arl.get(position).getCNE());
        holder.i_Nom_Prof.setText(arl.get(position).getTitre());
        holder.i_titre.setText(arl.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return arl.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView i_titre,i_Nom_Prof,i_env;;
        LinearLayout conteneur;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            i_env=itemView.findViewById(R.id.item_CNE);
            i_Nom_Prof=itemView.findViewById(R.id.item_ville);
            i_titre = itemView.findViewById(R.id.item_Nom);
            conteneur=itemView.findViewById(R.id.conteneur);
        }
    }
}
