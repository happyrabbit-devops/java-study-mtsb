package org.example.model.repository;

import org.example.model.Animal;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalRepositoryImpl implements AnimalRepository {

    @Override
    public Map<String, LocalDate> findLeapYearNames(Map<String, List<Animal>> animalMap) {
        Map<String, LocalDate> leapYearAnimals = new HashMap<>();
        animalMap.forEach((key, value) -> {
            value.forEach(animal -> {
                if (animal.getBirthDate().isLeapYear()) {
                    leapYearAnimals.put(animal.getClass().getSimpleName() + " " + animal.getName(), animal.getBirthDate());
                }
            });
        });
        return leapYearAnimals;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(Map<String, List<Animal>> animalMap, int age) {
        Map<Animal, Integer> olderAnimals = new HashMap<>();
        animalMap.values().forEach(animalList -> {
            animalList.forEach(animal -> {
                int animalAge = animal.calculateAge();
                if (animalAge > age) {
                    olderAnimals.put(animal, animalAge);
                }
            });
        });
        if (olderAnimals.isEmpty()) {
            Animal oldestAnimal = findOldestAnimal(animalMap);
            olderAnimals.put(oldestAnimal, oldestAnimal.calculateAge());
        }
        return olderAnimals;
    }

    private Animal findOldestAnimal(Map<String, List<Animal>> animalMap) {
        Animal oldestAnimal = null;
        int maxAge = Integer.MIN_VALUE;
        for (List<Animal> animalList : animalMap.values()) {
            for (Animal animal : animalList) {
                int animalAge = animal.calculateAge();
                if (animalAge > maxAge) {
                    maxAge = animalAge;
                    oldestAnimal = animal;
                }
            }
        }
        return oldestAnimal;
    }

    @Override
    public Map<String, Integer> findDuplicate(Map<String, List<Animal>> animalMap) {
        Map<String, Integer> duplicates = new HashMap<>();
        Map<String, Integer> animalCount = new HashMap<>();

        animalMap.values().forEach(animalList -> {
            animalList.forEach(animal -> {
                String animalString = animal.getClass().getSimpleName();
                int count = animalCount.getOrDefault(animalString, 0);
                if (count > 0) {
                    duplicates.put(animalString, count + 1);
                }
                animalCount.put(animalString, count + 1);
            });
        });

        return duplicates;
    }
}
