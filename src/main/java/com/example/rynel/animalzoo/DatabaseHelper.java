package com.example.rynel.animalzoo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rynel on 10/2/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Persons.db";

    public static final  String TABLE_NAME = "Persons";
    public static final  String COLUMN_ANIMAL= "Name";
    public static final  String COLUMN_SPECIES = "Age";
    public static final  String COLUMN_DESCRIPTION = "Gender";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //creating table with animal values to be inserted
    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ANIMAL + " TEXT PRIMARY KEY, " +
                COLUMN_SPECIES + " TEXT, " + COLUMN_DESCRIPTION + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    //update table version through each change via increment
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

        sqLiteDatabase.execSQL("DROP TABLE ID EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //list of animal and their properties to be saved
    public long saveAnimal(Animal animal){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ANIMAL, animal.getAnimal());
        contentValues.put(COLUMN_SPECIES, animal.getSpecies());
        contentValues.put(COLUMN_DESCRIPTION, animal.getDescription());

        long isSaved =  database.insert(TABLE_NAME, null, contentValues);

        return isSaved;
    }

    //retrieving list of animals stored in SQLite
    public List<Animal> getAnimalList(){
        List<Animal> animalList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = database.rawQuery(QUERY, null);

        if(cursor.moveToFirst()){
            do{
                Animal animal = new Animal(cursor.getString(0), cursor.getString(1),
                cursor.getString(2));
                animalList.add(animal);
            } while (cursor.moveToNext());
        }

        return animalList;

    }

}
