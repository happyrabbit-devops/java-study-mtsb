package org.example.model.repository;

import org.example.model.Animal;
import org.example.model.Cat;
import org.example.repository.AnimalRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AnimalRepositoryImplTest {

    @Test
    void testFindLeapYearNames() {
        var animalRepo = new AnimalRepositoryImpl();
        Map<String, List<Animal>> animalMap = new HashMap<>();
        List<Animal> animals = new ArrayList<>();
        var leapYearAnimal = new Cat("Home", "Barsik", 4300.573, "Angry Cat", LocalDate.of(2012, 2, 29));
        animals.add(leapYearAnimal);
        animalMap.put("Cat", animals);
        var result = animalRepo.findLeapYearNames(animalMap);
        assertEquals(1, result.size());
        assertEquals(LocalDate.of(2012, 2, 29), result.get("Cat Barsik"));
    }

    @Test
    void testFindOlderAnimal() {
        var animalRepo = new AnimalRepositoryImpl();
        Map<String, List<Animal>> animalMap = new HashMap<>();
        List<Animal> animals = new ArrayList<>();
        var olderAnimal1 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(1010, 1, 1));
        var olderAnimal2 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(1910, 1, 1));
        var olderAnimal3 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(2010, 1, 1));
        var olderAnimal4 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(2024, 1, 1));
        animals.add(olderAnimal1);
        animals.add(olderAnimal2);
        animals.add(olderAnimal3);
        animals.add(olderAnimal4);
        animalMap.put("Cat", animals);
        var result = animalRepo.findOlderAnimal(animalMap, 5);
        assertEquals(3, result.size());
        assertEquals(1014, result.get(olderAnimal1));
        var result2 = animalRepo.findOlderAnimal(animalMap, 1010);
        assertEquals(1, result2.size());
        assertEquals(1014, result2.get(olderAnimal1));
    }

    @Test
    void testFindDuplicate() {
        var animalRepo = new AnimalRepositoryImpl();
        Map<String, List<Animal>> animalMap = new HashMap<>();
        List<Animal> animals = new ArrayList<>();
        var animal1 = new Cat("Home", "Cat Barsik", 4300.573, "Angry Cat", LocalDate.of(2015, 3, 10));
        var animal2 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(2015, 4, 5));
        animals.add(animal1);
        animals.add(animal2);
        animalMap.put("Cat", animals);
        Map<String, Integer> result = animalRepo.findDuplicate(animalMap);
        assertEquals(1, result.size());
        System.out.println();
        assertEquals(2, result.get("Cat"));
    }
    @Test
    void testFindAverageAge() {
        var repository = new AnimalRepositoryImpl();
        var animal1 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(1010, 1, 1));
        var animal2 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(1910, 1, 1));
        var animal3 = new Cat("Home", "Cat Murzik", 4300.573, "Angry Cat", LocalDate.of(2010, 1, 1));
        List<Animal> animals = Arrays.asList(animal1, animal2, animal3);
        double averageAge = repository.findAverageAge(animals);
        var currentYear = LocalDate.now().getYear();
        assertEquals((double) (currentYear - 1010 + currentYear - 1910 + currentYear - 2010) / 3, averageAge);
    }

    @Test
    void testFindOldAndExpensive() {
        var repository = new AnimalRepositoryImpl();
        var animal1 = new Cat("Home", "Cat Murzik 1", 100, "Angry Cat", LocalDate.of(2020, 1, 1));
        var animal2 = new Cat("Home", "Cat Murzik 2", 200, "Angry Cat", LocalDate.of(2024, 1, 1));
        var animal3 = new Cat("Home", "Cat Murzik 3", 400, "Angry Cat", LocalDate.of(2010, 1, 1));
        List<Animal> animals = Arrays.asList(animal1, animal2, animal3);
        var oldAndExpensive = repository.findOldAndExpensive(animals);
        assertEquals(1, oldAndExpensive.size());
        assertEquals("Cat Murzik 3", oldAndExpensive.get(0).getName());
    }

    @Test
    void testFindMinCostAnimals() {
        var repository = new AnimalRepositoryImpl();
        var animal1 = new Cat("Home", "Cat Murzik 1", 4300.573, "Angry Cat", LocalDate.of(1010, 1, 1));
        var animal2 = new Cat("Home", "Cat Murzik 2", 4300.573, "Angry Cat", LocalDate.of(1910, 1, 1));
        var animal3 = new Cat("Home", "Cat Murzik 3", 4300.573, "Angry Cat", LocalDate.of(2010, 1, 1));
        List<Animal> animals = Arrays.asList(animal1, animal2, animal3);
        var minCostAnimals = repository.findMinCostAnimals(animals);
        assertEquals(3, minCostAnimals.size());
        assertEquals("Cat Murzik 3", minCostAnimals.get(0));
        assertEquals("Cat Murzik 2", minCostAnimals.get(1));
        assertEquals("Cat Murzik 1", minCostAnimals.get(2));
    }
}