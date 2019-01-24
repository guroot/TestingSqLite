package com.example.testingsqlite.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class AnimalDataAccess extends SQLiteOpenHelper {


    private static int VERSION = 1;
    private static String NAME = "ferme.db";
    private SQLiteDatabase db;

    public AnimalDataAccess(@androidx.annotation.Nullable Context context) {
        super(context, NAME, VERSION, null);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE animal(id int Primary Key AUTOINCREMENT," +
               "nom TEXT, date_naissance TEXT, commentaire TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long insert(Animal animal){
        ContentValues values = new ContentValues();
        values.put("nom",animal.getNom());
        values.put("date_naissance",animal.getDateNaissance().toString());
        values.put("commentaire",animal.getCommentaire());
        return db.insert("animal",null,values);
    }


    public Animal getById(int id){
        Animal animal = new Animal();
        Cursor c1 = db.query("animal",new String[]{"id","nom","date_naissance","commentaire"}
        , "id=?",new String[]{String.valueOf(id)},null,null,null);
        c1.moveToFirst();
        animal.setId(c1.getInt(0));
        animal.setNom(c1.getString(1));
        animal.setDateNaissance(new Date(c1.getString(2)));
        animal.setCommentaire(c1.getString(3));
        return animal;
    }

    public int deleteById(int id){
        return db.delete("animal","id = ?",new String[]{String.valueOf(id)});
    }

}
