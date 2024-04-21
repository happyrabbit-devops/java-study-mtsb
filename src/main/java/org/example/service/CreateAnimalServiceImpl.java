package org.example.service;

import org.example.model.Animal;
import org.example.storage.AnimalStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    @Override
    public Map<String, List<Animal>> createAnimals(int n) {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Animal animal = createAnimal(i);
            animalsMap.computeIfAbsent(animal.getClass().getName(), key -> new ArrayList<>()).add(animal);
        }
        return animalsMap;
    }

    @Override
    public Map<String, List<Animal>> createAnimalsWithDoWhileLoop(int n) {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        int i = 0;
        do {
            Animal animal = createAnimal(i);
            animalsMap.computeIfAbsent(animal.getClass().getName(), key -> new ArrayList<>()).add(animal);
            i++;
        } while (i < n);
        return animalsMap;
    }
}
