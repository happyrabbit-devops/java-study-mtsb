package org.example.model;

public class Lion extends Predator {

    public Lion(String breed, String name, double cost, String character) {
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.predatorTribe = PredatorTribes.SOUTH;
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