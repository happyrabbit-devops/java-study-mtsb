package org.example.model.repository;

import org.example.model.Animal;
import org.example.model.Cat;
import org.example.repository.AnimalRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.example.model.Habitat.HOME;
import static org.example.repository.AnimalRepositoryImpl.OLDER_RESULTS_FILE_PATH;
import static org.example.utils.TextFileUtils.readAndDecodeValues;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalRepositoryImplTest {

    @Test
    void testFindLeapYearNames() {
        var animalRepo = new AnimalRepositoryImpl();
        Map<String, List<Animal>> animalMap = new HashMap<>();
        List<Animal> animals = new ArrayList<>();
        var leapYearAnimal = new Cat(1, HOME, "Barsik", LocalDate.of(2012, 2, 29), 4300.573, "Angry Cat");
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
        var olderAnimal1 = new Cat(1, HOME, "Cat Murzik", LocalDate.of(1010, 1, 1), 4300.573, "Angry Cat");
        var olderAnimal2 = new Cat(2,HOME, "Cat Murzik", LocalDate.of(1910, 1, 1),  4300.573, "Angry Cat");
        var olderAnimal3 = new Cat(3,HOME, "Cat Murzik", LocalDate.of(2010, 1, 1), 4300.573, "Angry Cat");
        var olderAnimal4 = new Cat(4,HOME, "Cat Murzik", LocalDate.of(2024, 1, 1), 4300.573, "Angry Cat");
        animals.add(olderAnimal1);
        animals.add(olderAnimal2);
        animals.add(olderAnimal3);
        animals.add(olderAnimal4);
        animalMap.put("Cat", animals);
        var result = animalRepo.findOlderAnimal(animalMap, 5);
        readAndDecodeValues(OLDER_RESULTS_FILE_PATH);
        assertEquals(3, result.size());
        assertEquals(1014, result.get(olderAnimal1));
        var result2 = animalRepo.findOlderAnimal(animalMap, 2010);
        readAndDecodeValues(OLDER_RESULTS_FILE_PATH);
        assertEquals(1, result2.size());
        assertEquals(1014, result2.get(olderAnimal1));
    }

    @Test
    void testFindDuplicate() {
        var animalRepo = new AnimalRepositoryImpl();
        Map<String, List<Animal>> animalMap = new HashMap<>();
        List<Animal> animals = new ArrayList<>();
        var animal1 = new Cat(1, HOME, "Cat Barsik", LocalDate.of(2015, 3, 10), 4300.573, "Angry Cat");
        var animal2 = new Cat(2, HOME, "Cat Murzik", LocalDate.of(2015, 4, 5), 4300.573, "Angry Cat");
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
        var animal1 = new Cat(1, HOME, "Cat Murzik", LocalDate.of(1010, 1, 1), 4300.573, "Angry Cat");
        var animal2 = new Cat(2, HOME, "Cat Murzik", LocalDate.of(1910, 1, 1), 4300.573, "Angry Cat");
        var animal3 = new Cat(3, HOME, "Cat Murzik", LocalDate.of(2010, 1, 1), 4300.573, "Angry Cat");
        List<Animal> animals = Arrays.asList(animal1, animal2, animal3);
        double averageAge = repository.findAverageAge(animals);
        var currentYear = LocalDate.now().getYear();
        assertEquals((double) (currentYear - 1010 + currentYear - 1910 + currentYear - 2010) / 3, averageAge);
    }

    @Test
    void testFindOldAndExpensive() {
        var repository = new AnimalRepositoryImpl();
        var animal1 = new Cat(1, HOME, "Cat Murzik 1", LocalDate.of(2020, 1, 1), 100, "Angry Cat");
        var animal2 = new Cat(2, HOME, "Cat Murzik 2", LocalDate.of(2024, 1, 1), 200, "Angry Cat");
        var animal3 = new Cat(3, HOME, "Cat Murzik 3", LocalDate.of(2010, 1, 1), 400, "Angry Cat");
        List<Animal> animals = Arrays.asList(animal1, animal2, animal3);
        var oldAndExpensive = repository.findOldAndExpensive(animals);
        assertEquals(1, oldAndExpensive.size());
        assertEquals("Cat Murzik 3", oldAndExpensive.get(0).getName());
    }

    @Test
    void testFindMinCostAnimals() {
        var repository = new AnimalRepositoryImpl();
        var animal1 = new Cat(1, HOME, "Cat Murzik 1", LocalDate.of(1010, 1, 1), 4300.573, "Angry Cat");
        var animal2 = new Cat(2, HOME, "Cat Murzik 2", LocalDate.of(1910, 1, 1), 4300.573, "Angry Cat");
        var animal3 = new Cat(3, HOME, "Cat Murzik 3", LocalDate.of(2010, 1, 1), 4300.573, "Angry Cat");
        List<Animal> animals = Arrays.asList(animal1, animal2, animal3);
        var minCostAnimals = repository.findMinCostAnimals(animals);
        assertEquals(3, minCostAnimals.size());
        assertEquals("Cat Murzik 3", minCostAnimals.get(0));
        assertEquals("Cat Murzik 2", minCostAnimals.get(1));
        assertEquals("Cat Murzik 1", minCostAnimals.get(2));
    }
}