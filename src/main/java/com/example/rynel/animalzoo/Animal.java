package com.example.rynel.animalzoo;

/**
 * Created by rynel on 10/2/2017.
 */

public class Animal {

    private String animal, species, description;

    public Animal(String animal, String species, String description){
        this.animal = animal;
        this.species = species;
        this.description = description;
    }
    public String getAnimal() {

        return animal;
    }

    public void setAnimal(String animal) {

        this.animal = animal;
    }

    public String getSpecies() {

        return species;
    }

    public void setSpecies(String species) {

        this.species = species;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

}
