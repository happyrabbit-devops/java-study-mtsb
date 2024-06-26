package org.example.model;

import java.time.LocalDate;

public class Lynx extends Predator {

    public Lynx(int id, Habitat habitat, String name, LocalDate birthDate, double cost, String character) {
        this.id = id;
        this.habitat = habitat;
        this.name = name;
        this.birthDate = birthDate;
        this.cost = cost;
        this.character = character;
        this.predatorTribe = PredatorTribes.SOUTH;
    }

    @Override
    public Habitat getHabitat() {
        return this.habitat;
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

    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
