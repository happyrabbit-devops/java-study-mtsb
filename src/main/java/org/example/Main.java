package org.example;

import org.example.exception.InvalidAnimalBirthDateException;
import org.example.exception.InvalidAnimalException;
import org.example.service.CreateAnimalServiceImpl;
import org.example.service.SearchServiceImpl;
import org.example.storage.AnimalStorage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

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
        createAnimalService.createAnimals(zoo1);
        createAnimalService.createAnimals(33, zoo2);
        createAnimalService.createAnimalsWithDoWhileLoop(55, zoo3);

        createAnimalService.createAnimals(3, testZoo);
        testZoo.getAnimals().get(0).setBirthDate(formatBirthDate("22.03.1995"));
        testZoo.getAnimals().get(1).setBirthDate(formatBirthDate("11.04.2005"));
        testZoo.getAnimals().add(null);

        for (var animal : testZoo.getAnimals()) {
            try {
                logger.fine(searchService.checkLeapYearAnimal(animal));
            } catch (InvalidAnimalException | InvalidAnimalBirthDateException e) {
                logger.info(e.getMessage());
            }
        }
    }
}