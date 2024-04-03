package org.example.model;

import static org.example.model.HerbFood.SHRIMP;

public class Whale extends Herbivorous {

    public Whale(String breed, String name, double cost, String character) {
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.herbFood = SHRIMP;
    }

    @Override
    public String getBreed() {
        return this.breed;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getCost() {
        return this.cost;
    }

    @Override
    public String getCharacter() {
        return this.character;
    }
}
