package org.example.model.repository;

import org.example.model.Animal;
import org.example.model.Cat;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AnimalRepositoryImplTest {

    @Test
    void testFindLeapYearNames() {
        AnimalRepositoryImpl animalRepo = new AnimalRepositoryImpl();
        Map<String, List<Animal>> animalMap = new HashMap<>();
        List<Animal> animals = new ArrayList<>();
        Animal leapYearAnimal = new Cat("Home", "Barsik", 4300.573, "Angry Cat", LocalDate.of(2012, 2, 29));
        animals.add(leapYearAnimal);
        animalMap.put("Cat", animals);
        Map<String, LocalDate> result = animalRepo.findLeapYearNames(animalMap);
        assertEquals(1, result.size());
        assertEquals(LocalDate.of(2012, 2, 29), result.get("Cat Barsik"));
    }

    @Test
    void testFindOlderAnimal() {
        AnimalRepositoryImpl animalRepo = new AnimalRepositoryImpl();
        Map<String, List<Animal>> animalMap = new HashMap<>();
        List<Animal> animals = new ArrayList<>();
        Animal olderAnimal1 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(1010, 1, 1));
        Animal olderAnimal2 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(1910, 1, 1));
        Animal olderAnimal3 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(2010, 1, 1));
        Animal olderAnimal4 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(2024, 1, 1));
        animals.add(olderAnimal1);
        animals.add(olderAnimal2);
        animals.add(olderAnimal3);
        animals.add(olderAnimal4);
        animalMap.put("Cat", animals);
        Map<Animal, Integer> result = animalRepo.findOlderAnimal(animalMap, 5);
        assertEquals(3, result.size());
        assertEquals(1014, result.get(olderAnimal1));
        Map<Animal, Integer> result2 = animalRepo.findOlderAnimal(animalMap, 1015);
        assertEquals(1, result2.size());
        assertEquals(1014, result2.get(olderAnimal1));
    }

    @Test
    void testFindDuplicate() {
        AnimalRepositoryImpl animalRepo = new AnimalRepositoryImpl();
        Map<String, List<Animal>> animalMap = new HashMap<>();
        List<Animal> animals = new ArrayList<>();
        Animal animal1 = new Cat("Home", "Cat Barsik", 4300.573, "Angry Cat", LocalDate.of(2015, 3, 10));
        Animal animal2 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(2015, 4, 5));
        animals.add(animal1);
        animals.add(animal2);
        animalMap.put("Cat", animals);
        Map<String, Integer> result = animalRepo.findDuplicate(animalMap);
        assertEquals(1, result.size());
        System.out.println();
        assertEquals(2, result.get("Cat"));
    }
}