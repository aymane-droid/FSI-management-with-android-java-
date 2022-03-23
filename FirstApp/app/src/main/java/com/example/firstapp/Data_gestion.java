package com.example.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Data_gestion extends SQLiteOpenHelper {
    public Data_gestion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Etudiant(CNE text primary key , nom text , ville text)");

        db.execSQL("create table Professeur(CIN text primary key , nom text , grad text)");

        db.execSQL("create table Module(titre text primary key, env_hor int , CIN_P text ," +
                " FOREIGN KEY (CIN_P) REFERENCES Professeur(CIN) on delete cascade ON UPDATE CASCADE)");


        db.execSQL("create table Inscription(CNE text , titre text , date text,primary key(CNE,titre),foreign key (titre) references Module(titre) ON DELETE CASCADE,foreign key (CNE) references Etudiant(CNE) ON DELETE CASCADE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /////fonctions d'Etudiant
    void inserer_Etu(String CNE,String nom,String ville){

        SQLiteDatabase database=getWritableDatabase();
        ContentValues v=new ContentValues();

        v.put("CNE",CNE);
        v.put("nom",nom);
        v.put("ville",ville);


        database.insert("Etudiant",null,v);
    }

    ArrayList<Etudiant> afficher_Mod_CNE_1(){
        ArrayList<Etudiant> arl = new ArrayList<Etudiant>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select CNE,nom from Etudiant",null);

        while (c.moveToNext())
            arl.add(new Etudiant(c.getString(0),c.getString(1))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    void effacer(){

        SQLiteDatabase database=getWritableDatabase();
        database.execSQL("delete from Etudiant");

    }

    void effacer_Etu(String r){
        SQLiteDatabase database = getWritableDatabase();

        //database.execSQL("delete from Prd where ref="+r);

        database.delete("Etudiant","CNE=?",new String[]{r});
    }

    void MAJ_Etu(String r,String d,String p){
        SQLiteDatabase database=getWritableDatabase();

        ContentValues v=new ContentValues();

        v.put("CNE",r);
        v.put("nom",d);
        v.put("ville",p);

        database.update("Etudiant",v,"CNE=?",new String[]{r});
    }

    ArrayList<Etudiant> afficher_Etu(){
        ArrayList<Etudiant> arl = new ArrayList<Etudiant>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Etudiant order by nom asc",null);


        while (c.moveToNext())
            arl.add(new Etudiant(c.getString(0),c.getString(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    ArrayList<Etudiant> recherche_CNE_Etu(String d){
        ArrayList<Etudiant> arl = new ArrayList<Etudiant>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Etudiant where CNE like ?",new String[]{d+"%"});

        while (c.moveToNext())
            arl.add(new Etudiant(c.getString(0),c.getString(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    ArrayList<Etudiant> recherche_NOM_Etu(String n){
        ArrayList<Etudiant> arl = new ArrayList<Etudiant>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Etudiant where nom like ?",new String[]{n+"%"});

        while (c.moveToNext())
            arl.add(new Etudiant(c.getString(0),c.getString(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    ArrayList<Etudiant> recherche_VILLE_Etu(String v){
        ArrayList<Etudiant> arl = new ArrayList<Etudiant>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Etudiant where ville like ?",new String[]{v+"%"});

        while (c.moveToNext())
            arl.add(new Etudiant(c.getString(0),c.getString(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    /////fonctions d'Professeur
    ArrayList<String> afficher_Nom_CIN(){
        ArrayList<String> arl = new ArrayList<String>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select nom from Professeur",null);

        while (c.moveToNext())
            arl.add(c.getString(0)); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    ArrayList<Professeur> afficher_Nom_CIN_1(){
        ArrayList<Professeur> arl = new ArrayList<Professeur>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select CIN,nom from Professeur",null);

        while (c.moveToNext())
            arl.add(new Professeur(c.getString(0),c.getString(1))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    void inserer_Prof(String CIN,String nom,String grad){

        SQLiteDatabase database=getWritableDatabase();
        ContentValues v=new ContentValues();

        v.put("CIN",CIN);
        v.put("nom",nom);
        v.put("grad",grad);

        database.insert("Professeur",null,v);
    }

    void effacer_Prof(){

        SQLiteDatabase database=getWritableDatabase();
        database.execSQL("delete from Professeur");

    }

    void effacer_Prof(String r){
        SQLiteDatabase database = getWritableDatabase();

        //database.execSQL("delete from Prd where ref="+r);

        database.delete("Professeur","CIN=?",new String[]{r});
    }

    void MAJ_Prof(String r,String d,String p){
        SQLiteDatabase database=getWritableDatabase();

        ContentValues v=new ContentValues();

        v.put("CIN",r);
        v.put("nom",d);
        v.put("grad",p);

        database.update("Professeur",v,"CIN=?",new String[]{r});
    }

    ArrayList<Professeur> afficher_Prof(){
        ArrayList<Professeur> arl = new ArrayList<Professeur>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Professeur order by nom asc",null);

        while (c.moveToNext())
            arl.add(new Professeur(c.getString(0),c.getString(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    ArrayList<Professeur> recherche_CIN_Prof(String d){
        ArrayList<Professeur> arl = new ArrayList<Professeur>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Professeur where CIN like ?",new String[]{d+"%"});

        while (c.moveToNext())
            arl.add(new Professeur(c.getString(0),c.getString(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    ArrayList<Professeur> recherche_NOM_Prof(String n){
        ArrayList<Professeur> arl = new ArrayList<Professeur>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Professeur where nom like ?",new String[]{n+"%"});

        while (c.moveToNext())
            arl.add(new Professeur(c.getString(0),c.getString(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    ArrayList<Professeur> recherche_GRAD_Prof(String v){
        ArrayList<Professeur> arl = new ArrayList<Professeur>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Professeur where grad like ?",new String[]{v+"%"});

        while (c.moveToNext())
            arl.add(new Professeur(c.getString(0),c.getString(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    /////fonction de modules
    void inserer_Mod(String titre,int env,String cin){

        SQLiteDatabase database=getWritableDatabase();
        ContentValues v=new ContentValues();

        v.put("titre",titre);
        v.put("env_hor",env);
        v.put("CIN_P",cin);

        database.insert("Module",null,v);
    }

    void ins(String titre,int env){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues v=new ContentValues();

        v.put("titre",titre);
        v.put("env_hor",env);
        v.put("CIN_P","cin");

        database.insert("Module",null,v);
    }

    ArrayList<Module> afficher_Module(){
        ArrayList<Module> arl = new ArrayList<Module>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select titre,env_hor,CIN_P from Module",null);

        while (c.moveToNext())
            arl.add(new Module(c.getString(0),c.getInt(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    void effacer_Module(){

        SQLiteDatabase database=getWritableDatabase();
        database.execSQL("delete from Module");

    }

    ArrayList<Module> affiche_titre(){
        ArrayList<Module> arl = new ArrayList<Module>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select titre from Module",null);

        while (c.moveToNext())
            arl.add(new Module(c.getString(0))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    ArrayList<Module> recherche_NOM_Module(String d){
        ArrayList<Module> arl = new ArrayList<Module>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Module where titre like ?",new String[]{d+"%"});

        while (c.moveToNext())
            arl.add(new Module(c.getString(0),c.getInt(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    ArrayList<Module> recherche_NOM_Prof_mod(String d){
        ArrayList<Module> arl = new ArrayList<Module>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Module where CIN_P like ?",new String[]{d+"%"});

        while (c.moveToNext())
            arl.add(new Module(c.getString(0),c.getInt(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    ArrayList<Module> recherche_ENV_HOR(int d){
        ArrayList<Module> arl = new ArrayList<Module>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Module where env_hor > ?",new String[]{String.valueOf(d)});

        while (c.moveToNext())
            arl.add(new Module(c.getString(0),c.getInt(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    void effacer_mod(String r){
        SQLiteDatabase database = getWritableDatabase();

        //database.execSQL("delete from Prd where ref="+r);

        database.delete("Module","titre=?",new String[]{r});
    }


    /////fonction d'inscription
    void insetion_insp(String CNE,String titre,String date){

        SQLiteDatabase database=getWritableDatabase();
        ContentValues v=new ContentValues();

        v.put("CNE",CNE);
        v.put("titre",titre);
        v.put("date",date);

        database.insert("Inscription",null,v);
    }

    ArrayList<Inscription> afficher_insp(){
        ArrayList<Inscription> arl = new ArrayList<Inscription>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Inscription",null);

        while (c.moveToNext())
            arl.add(new Inscription(c.getString(0),c.getString(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    void a(){

        SQLiteDatabase database=getWritableDatabase();
        database.execSQL("create table Inscription(CNE text , titre text , date text,primary key(CNE,titre),foreign key (titre) references Module(titre) ON DELETE CASCADE,foreign key (CNE) references Etudiant(CNE) ON DELETE CASCADE)");

    }

    void effacer_inc(){

        SQLiteDatabase database=getWritableDatabase();
        database.execSQL("delete from Inscription");

    }

    ArrayList<Inscription> recherche_NOM_Et(String d){
        ArrayList<Inscription> arl = new ArrayList<Inscription>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Inscription where CNE like ?",new String[]{d});

        while (c.moveToNext())
            arl.add(new Inscription(c.getString(0),c.getString(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    ArrayList<Inscription> recherche_NOM_Mo(String d){
        ArrayList<Inscription> arl = new ArrayList<Inscription>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Inscription where titre like ?",new String[]{d});

        while (c.moveToNext())
            arl.add(new Inscription(c.getString(0),c.getString(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    ArrayList<Inscription> recherche_date(String d){
        ArrayList<Inscription> arl = new ArrayList<Inscription>();

        SQLiteDatabase database=getReadableDatabase();

        Cursor c = database.rawQuery("select * from Inscription where date like ?",new String[]{d+"%"});

        while (c.moveToNext())
            arl.add(new Inscription(c.getString(0),c.getString(1),c.getString(2))); // si on a n colone donc j'ai utilis cursor.getcolomnindex

        return  arl;
    }

    void effacer_inscription(String r,String v){
        SQLiteDatabase database = getWritableDatabase();

        //database.execSQL("delete from Prd where ref="+r);

        database.delete("Inscription","CNE=? and titre=?",new String[]{r,v});
    }



}


