package com.example.rynel.animalzoo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rynel.savingdata.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private TextView tvGetDataFromSharedPref;
    private EditText etSaveDataToSharedPref;
    private double btnClearAllSharedPref;
    private EditText etAnimal;
    private EditText etAnimalSpecies;
    private EditText etAnimalDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating save and binding to id in xml like in training
        etSaveDataToSharedPref = (EditText) findViewById(R.id.etSaveDataToSharedPref);
        tvGetDataFromSharedPref = (TextView) findViewById(R.id.getDataToSharedPref);

        //for SQL: binding animal properties with appropriate ids
        etAnimal = (EditText) findViewById(R.id.etAnimal);
        etAnimalSpecies = (EditText) findViewById(R.id.etAnimalSpecies);
        etAnimalDescription = (EditText) findViewById(R.id.etAnimalDescription);




    }

    //learning to write and save data
    public void usingSharedPref(View view){

        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (view.getId()){

            case R.id.btnSaveDataToSharedPref:
                editor.putString("data", etSaveDataToSharedPref.getText().toString());
                boolean isSaved = editor.commit();

                if(isSaved)
                    Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();

                break;
            case R.id.getDataToSharedPref:
                String data = sharedPreferences.getString("data", "defaultValue");
                tvGetDataFromSharedPref.setText(data);

                editor.clear().commit();

                break;

            case R.id.btnClearAllSharedPref:

                boolean isCleared = editor.clear().commit();
                        if (isCleared)
                            Toast.makeText(this, "Data cleared", Toast.LENGTH_SHORT).show();

                        else
                            Toast.makeText(this,"Data not cleared", Toast.LENGTH_SHORT).show();

                break;


        }
    }

    public void usingSQLite(View view){

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        switch (view.getId()){

            case R.id.btnSaveAnimalToDB:

                String animal = etAnimal.getText().toString();
                String animalSpecies = etAnimalSpecies.getText().toString();
                String animalDescription = etAnimalDescription.getText().toString();


                Animal animalia = new Animal(animal, animalSpecies, animalDescription);

                long rowId = databaseHelper.saveAnimal(animalia);

                Toast.makeText(this, "Row id: " + rowId, Toast.LENGTH_SHORT).show();

                break;

            case R.id.btnGetAnimalFromDB:

                List<Animal> animalList = databaseHelper.getAnimalList();
                for(int i = 0; i <animalList.size(); i++)
                {
                    Log.d(TAG, "usingSQLite: " + animalList.get(i).toString());
                }

                Intent intent = new Intent(this, AnimalListActivity.class);
                startActivity(intent);

                break;
        }

        //TODO: 10/2/17 Create a database to save the data
    }


}
