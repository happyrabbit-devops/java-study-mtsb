package org.example;

import org.example.exception.InvalidAnimalBirthDateException;
import org.example.exception.InvalidAnimalException;
import org.example.model.Animal;
import org.example.service.CreateAnimalServiceImpl;
import org.example.service.SearchServiceImpl;
import org.example.storage.AnimalStorage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static java.util.Objects.nonNull;

public class Main {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    static Logger logger = Logger.getLogger(Main.class.getName());

    public static LocalDate formatBirthDate(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }

    static SearchServiceImpl searchService = new SearchServiceImpl();

    public static void main(String[] args) {

        var zoo1 = new AnimalStorage();
        var zoo2 = new AnimalStorage();
        var zoo3 = new AnimalStorage();
        var testZoo = new AnimalStorage();

        var createAnimalService = new CreateAnimalServiceImpl();

        zoo1.setAnimals(createAnimalService.createAnimals());
        zoo2.setAnimals(createAnimalService.createAnimals(33));
        zoo3.setAnimals(createAnimalService.createAnimalsWithDoWhileLoop(55));

        testZoo.setAnimals(createAnimalService.createAnimals(3));

        Map<String, List<Animal>> animalsMap = testZoo.getAnimals();

        List<String> keysList = new ArrayList<>(animalsMap.keySet());

        // Указываем дату рождения для первого животного
        animalsMap.get(keysList.get(0)).get(0).setBirthDate(formatBirthDate("22.03.1995"));

        // Если в карте больше 1 вида животного, то укажем дату рождения для первого во втором типе
        if (keysList.size() > 1) {
            animalsMap.get(keysList.get(1)).get(0).setBirthDate(formatBirthDate("11.04.2005"));
        } else {
            // иначе у нас все 3 животных одного вида, добавим дату рождения для второго по порядку
            animalsMap.get(keysList.get(0)).get(1).setBirthDate(formatBirthDate("11.04.2005"));
        }

        // Добавим нулевой объект для ошибки
        animalsMap.get(keysList.get(0)).add(null);

        for (Map.Entry<String, List<Animal>> entry : animalsMap.entrySet()) {
            if (nonNull(entry.getValue())) {
                for (var animal : entry.getValue()) {
                    try {
                        logger.fine(searchService.checkLeapYearAnimal(animal));
                    } catch (InvalidAnimalException | InvalidAnimalBirthDateException e) {
                        logger.info(e.getMessage());
                    }
                }
            }
        }

    }
}