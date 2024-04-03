package org.example.service;

import org.example.model.Animal;
import org.example.storage.AnimalStorage;

import java.util.Random;
import java.util.logging.Logger;


public interface CreateAnimalService {

    Logger logger = Logger.getLogger(CreateAnimalService.class.getName());

    Random random = new Random();

    default Animal createAnimal(int i) {
        String[] animalNames = {"elephant", "lion", "cat", "tiger", "lynx", "whale"};
        Animal animal = AnimalFactory.createAnimal(animalNames[random.nextInt(animalNames.length)]);
        var log = String.format("Создано %d животное: %s %s цена %.2f характер %s%n", i+1, animal.getBreed(), animal.getName(), animal.getCost(), animal.getCharacter());
        logger.info(log);
        return animal;
    }

    default void createAnimals(AnimalStorage storage) {
        int i = 0;
        while (i < 10) {
            storage.getAnimals().add(createAnimal(i));
            i++;
        }
    }

    void createAnimals(int n, AnimalStorage storage);
    void createAnimalsWithDoWhileLoop(int n, AnimalStorage storage);
}
