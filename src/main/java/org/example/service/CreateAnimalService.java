package org.example.service;

import org.example.model.Animal;
import org.example.model.Elephant;
import org.example.model.Lion;

import java.util.UUID;
import java.util.logging.Logger;

public interface CreateAnimalService {

    Logger logger = Logger.getLogger(CreateAnimalService.class.getName());

    default void createAnimal(int i) {
        Animal animal;
        if (i % 2 == 0) {
            animal = new Elephant("Indian", "Elephant " + UUID.randomUUID(), 5000.354, "Friendly");
        } else {
            animal = new Lion("Safari", "Lion "+ UUID.randomUUID(), 10000.573, "Angry");
        }
        var log = String.format("Created animal: %s %s cost %.2f character %s%n", animal.getBreed(), animal.getName(), animal.getCost(), animal.getCharacter());
        logger.info(log);
    }

    default void createAnimals() {
        int i = 0;
        while (i < 10) {
            createAnimal(i);
            i++;
        }
    }

    void createAnimals(int n);
    void createAnimalsWithDoWhileLoop(int n);
}
