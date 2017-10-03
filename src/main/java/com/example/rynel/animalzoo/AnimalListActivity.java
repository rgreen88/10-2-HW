package com.example.rynel.animalzoo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rynel.savingdata.R;

import java.util.List;

public class AnimalListActivity extends AppCompatActivity{

    private ListView lvAnimalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);

        lvAnimalList = (ListView) findViewById(R.id.lvAnimal);

        String[] values = new String[]{
                "value1",
                "value2",
                "value3",
                "value4",

        };

        //setting the listview with dummy data initialized above
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,values);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Animal> animalList = databaseHelper.getAnimalList();

        //using custom adapter
        AnimalListAdapter animalListAdapter = new AnimalListAdapter(this, R.layout.custom_list_item, animalList);
        lvAnimalList.setAdapter(animalListAdapter);
    }
}
