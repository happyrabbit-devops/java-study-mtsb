package org.example.service;

import org.example.model.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.utils.TextFileUtils.writeToLog;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    public static final String MALE_NAMES_LOCATION = "https://raw.githubusercontent.com/Raven-SL/ru-pnames-list/master/lists/male_names_rus.txt";
    public static final String FEMALE_NAMES_LOCATION = "https://raw.githubusercontent.com/Raven-SL/ru-pnames-list/master/lists/female_names_rus.txt";

    @Override
    public Map<String, List<Animal>> createAnimals(int n) {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Animal animal = createAnimal(i);
            animalsMap.computeIfAbsent(animal.getClass().getSimpleName(), key -> new ArrayList<>()).add(animal);
        }
        return animalsMap;
    }

    @Override
    public Map<String, List<Animal>> createAnimalsWithDoWhileLoop(int n) {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        int i = 0;
        do {
            Animal animal = createAnimal(i);
            animalsMap.computeIfAbsent(animal.getClass().getSimpleName(), key -> new ArrayList<>()).add(animal);
            i++;
        } while (i < n);
        return animalsMap;
    }

    public static void writeLogFileAnimal(Animal animal) {
        writeToLog(animal.getInstanceName());
    }
}
