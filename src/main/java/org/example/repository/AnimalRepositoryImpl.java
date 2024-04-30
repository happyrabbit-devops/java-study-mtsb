package org.example.repository;

import org.example.model.Animal;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.example.utils.TextFileUtils.getCurrentMethodName;
import static org.example.utils.TextFileUtils.writeToJson;

public class AnimalRepositoryImpl implements AnimalRepository {

    public static final String RESULTS_FILE_PATH = "src/main/resources/results/%s.json";
    public static final String OLDER_RESULTS_FILE_PATH = "src/main/resources/results/findOlderAnimal.json";

    @Override
    public Map<String, LocalDate> findLeapYearNames(Map<String, List<Animal>> animalMap) {
        return animalMap.values().stream()
                .flatMap(List::stream)
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(
                        animal -> animal.getClass().getSimpleName() + " " + animal.getName(),
                        Animal::getBirthDate
                ));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(Map<String, List<Animal>> animalMap, int age) {
        var olderAnimal =  animalMap.values().stream()
                .flatMap(List::stream)
                .filter(animal -> animal.calculateAge() > age)
                .collect(Collectors.toMap(Function.identity(), Animal::calculateAge));
        if (olderAnimal.isEmpty()) {
            var oldestAnimal = findOldestAnimal(animalMap);
            if (nonNull(oldestAnimal)) {
                olderAnimal.put(oldestAnimal, oldestAnimal.calculateAge());
            }
        }
        writeToJson(olderAnimal, String.format(RESULTS_FILE_PATH, getCurrentMethodName()));
        return olderAnimal;
    }



    private Animal findOldestAnimal(Map<String, List<Animal>> animalMap) {
        return animalMap.values().stream()
                .flatMap(List::stream)
                .max(Comparator.comparingInt(Animal::calculateAge))
                .orElse(null);
    }

    @Override
    public Map<String, Integer> findDuplicate(Map<String, List<Animal>> animalMap) {
        var duplicates = animalMap.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(animal -> animal.getClass().getSimpleName(),
                        Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().intValue()));
        writeToJson(duplicates, String.format(RESULTS_FILE_PATH, getCurrentMethodName()));
        return duplicates;
    }

    @Override
    public double findAverageAge(List<Animal> animals) {
        return animals.stream()
                .mapToDouble(Animal::calculateAge)
                .average()
                .orElse(0);
    }

    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animals) {
        double averageCost = animals.stream()
                .mapToDouble(Animal::getCost)
                .average()
                .orElse(0);

        var oldAndExpensive = animals.stream()
                .filter(animal -> animal.calculateAge() > 5 && animal.getCost() > averageCost)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .toList();
        writeToJson(oldAndExpensive, String.format(RESULTS_FILE_PATH, getCurrentMethodName()));
        return oldAndExpensive;
    }

    @Override
    public List<String> findMinCostAnimals(List<Animal> animals) {
        var minCostAnimals = animals.stream()
                .sorted(Comparator.comparingDouble(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .toList();
        writeToJson(minCostAnimals, String.format(RESULTS_FILE_PATH, getCurrentMethodName()));
        return minCostAnimals;
    }

}
