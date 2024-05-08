package org.example.service;

import org.example.model.Animal;

import java.util.*;
import java.util.logging.Logger;

import static org.example.service.CreateAnimalServiceImpl.writeLogFileAnimal;

public interface CreateAnimalService {

    Logger logger = Logger.getLogger(CreateAnimalService.class.getName());

    Random random = new Random();

    default Animal createAnimal(int i) {
        String[] animalNames = {"elephant", "lion", "cat", "tiger", "lynx", "whale"};
        Animal animal = AnimalFactory.createAnimal(animalNames[random.nextInt(animalNames.length)], i);
        var log = String.format("Создано %d животное: %s%n", i+1, animal.getInstanceName());
        logger.info(log);
        writeLogFileAnimal(animal);
        return animal;
    }

    default Map<String, List<Animal>> createAnimals() {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        int i = 0;
        while (i < 10) {
            Animal animal = createAnimal(i);
            animalsMap.computeIfAbsent(animal.getClass().getSimpleName(), key -> new ArrayList<>()).add(animal);
            i++;
        }
        return animalsMap;
    }

    Map<String, List<Animal>> createAnimals(int n);
    Map<String, List<Animal>> createAnimalsWithDoWhileLoop(int n);
}
