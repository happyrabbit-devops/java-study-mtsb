package org.example.model.repository;

import org.example.model.Animal;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AnimalRepository {
    Map<String, LocalDate> findLeapYearNames(Map<String, List<Animal>> animalMap);

    Map<Animal, Integer> findOlderAnimal(Map<String, List<Animal>> animalMap, int age);

    Map<String, Integer> findDuplicate(Map<String, List<Animal>> animalMap);
}


