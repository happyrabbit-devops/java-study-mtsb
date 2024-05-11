package org.example.repository;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Animal;
import org.example.service.CreateAnimalServiceImpl;
import org.example.storage.AnimalStorage;
import org.example.utils.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.example.utils.TextFileUtils.writeToJson;

@Repository
@Data
@Slf4j
public class AnimalRepositoryImpl implements AnimalRepository {

    public static final ResourceLoader resourceLoader = new ResourceLoader();

    public static final String OLDER_RESULTS_FILE_PATH = resourceLoader.getFilePath("results/findOlderAnimal.json");

    private final ApplicationContext context;

    private AnimalStorage storage;

    @Autowired
    public AnimalRepositoryImpl(ApplicationContext context) {
        this.context = context;
    }

    @PostConstruct
    public void init() {
        var createAnimalService = context.getBean(CreateAnimalServiceImpl.class);
        this.storage = new AnimalStorage(createAnimalService.createAnimals(100));
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        var leapYearNames = storage.getAnimals().values().stream()
                .flatMap(List::stream)
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(
                        animal -> animal.getClass().getSimpleName() + " " + animal.getName(),
                        Animal::getBirthDate
                ));
        writeToJson(leapYearNames, resourceLoader.getFilePath("results/findLeapYearNames.json"));
        return leapYearNames;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int age) {
        var olderAnimal =  storage.getAnimals().values().stream()
                .flatMap(List::stream)
                .filter(animal -> animal.calculateAge() > age)
                .collect(Collectors.toMap(Function.identity(), Animal::calculateAge));
        if (olderAnimal.isEmpty()) {
            var oldestAnimal = findOldestAnimal();
            if (nonNull(oldestAnimal)) {
                olderAnimal.put(oldestAnimal, oldestAnimal.calculateAge());
            }
        }
        writeToJson(olderAnimal, resourceLoader.getFilePath("results/findOlderAnimal.json"));
        return olderAnimal;
    }

    private Animal findOldestAnimal() {
        return storage.getAnimals().values().stream()
                .flatMap(List::stream)
                .max(Comparator.comparingInt(Animal::calculateAge))
                .orElse(null);
    }

    @Override
    public Map<String, Integer> findDuplicate() {
        var duplicates = storage.getAnimals().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(animal -> animal.getClass().getSimpleName(),
                        Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().intValue()));
        writeToJson(duplicates, resourceLoader.getFilePath("results/findDuplicate.json"));
        return duplicates;
    }

    @Override
    public double findAverageAge(String animalKey) {
        return storage.getAnimals().get(animalKey).stream()
                .mapToDouble(Animal::calculateAge)
                .average()
                .orElse(0);
    }

    @Override
    public List<Animal> findOldAndExpensive(String animalKey) {
        double averageCost = storage.getAnimals().get(animalKey).stream()
                .mapToDouble(Animal::getCost)
                .average()
                .orElse(0);

        var oldAndExpensive = storage.getAnimals().get(animalKey).stream()
                .filter(animal -> animal.calculateAge() > 5 && animal.getCost() > averageCost)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .toList();
        writeToJson(oldAndExpensive, resourceLoader.getFilePath("results/findOldAndExpensive.json"));
        return oldAndExpensive;
    }

    @Override
    public List<String> findMinCostAnimals(String animalKey) {
        var minCostAnimals = storage.getAnimals().get(animalKey).stream()
                .sorted(Comparator.comparingDouble(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .toList();
        writeToJson(minCostAnimals, resourceLoader.getFilePath("results/findMinCostAnimals.json"));
        return minCostAnimals;
    }

}
