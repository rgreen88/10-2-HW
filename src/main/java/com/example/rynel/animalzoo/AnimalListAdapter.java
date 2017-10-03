package com.example.rynel.animalzoo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rynel.savingdata.R;

import java.util.List;

/**
 * Created by rynel on 10/2/2017.
 */

public class AnimalListAdapter extends ArrayAdapter<Animal> {

    public AnimalListAdapter(@NonNull Context context, @LayoutRes int resource, List<Animal> animalList){
        super(context, resource, animalList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent){

        View view = convertView;
        /* Inflate view with custom layout xml */
        if(view==null) {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, null);
        }
        //bind all the views
        TextView tvAnimal = (TextView) view.findViewById(R.id.tvAnimal);
        TextView tvAnimalSpecies = (TextView) view.findViewById(R.id.tvAnimalSpecies);
        TextView tvAnimalDescription = (TextView) view.findViewById(R.id.tvAnimalDescription);

        //set the value to the binded views
        Animal animal = getItem(position);
        tvAnimal.setText(animal.getAnimal());
        tvAnimalSpecies.setText(animal.getSpecies());
        tvAnimalDescription.setText(animal.getDescription());

        return view;

    }


}
