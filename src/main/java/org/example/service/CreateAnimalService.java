package org.example.service;

import org.example.model.Animal;
import java.util.Random;
import java.util.logging.Logger;

public interface CreateAnimalService {

    Logger logger = Logger.getLogger(CreateAnimalService.class.getName());

    Random random = new Random();

    default void createAnimal(int i) {
        String[] animalNames = {"elephant", "lion", "cat", "tiger", "lynx", "whale"};
        Animal animal = AnimalFactory.createAnimal(animalNames[random.nextInt(animalNames.length)]);
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
