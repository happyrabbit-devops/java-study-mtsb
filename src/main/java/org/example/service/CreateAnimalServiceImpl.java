package org.example.service;

import java.util.logging.Logger;
import org.example.model.Animal;
import org.example.model.Elephant;
import org.example.model.Lion;

import java.util.UUID;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    Logger logger = Logger.getLogger(getClass().getName());

    public void createAnimal(int i) {
        Animal animal;
        if (i % 2 == 0) {
            animal = new Elephant("Indian", "Elephant " + UUID.randomUUID(), 5000.354, "Friendly");
        } else {
            animal = new Lion("Safari", "Lion "+ UUID.randomUUID(), 10000.573, "Angry");
        }
        var log = String.format("Created animal: %s %s cost %.2f character %s%n", animal.getBreed(), animal.getName(), animal.getCost(), animal.getCharacter());
        logger.info(log);
    }

    @Override
    public void createAnimals() {
        int i = 0;
        while (i < 10) {
            createAnimal(i);
            i++;
        }
    }

    @Override
    public void createAnimals(int n) {
        for (int i = 0; i < n; i++) {
            createAnimal(i);
        }
    }

    @Override
    public void createAnimalsWithDoWhileLoop(int n) {
        int i = 0;
        do {
            createAnimal(i);
            i++;
        } while (i < n);
    }
}
