package org.example.service;

import org.example.exception.InvalidAnimalBirthDateException;
import org.example.exception.InvalidAnimalException;
import org.example.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.example.Main.formatBirthDate;
import static org.junit.jupiter.api.Assertions.*;

class SearchServiceTest {
    SearchService searchService = new SearchServiceImpl();

    @DisplayName("Некорректный входящий объект")
    @ParameterizedTest
    @ValueSource(strings = {"null", " ", "invalid"})
    void testInvalidAnimalInput(String input) {
        assertThrows(InvalidAnimalException.class, () -> searchService.checkLeapYearAnimal(null));
    }

    @DisplayName("У животного не задана дата рождения")
    @ParameterizedTest
    @ValueSource(classes = {Lynx.class, Elephant.class})
    void testAnimalWithoutBirthDate(Class<?> animalClass) throws Exception {
        var animal = (Animal) animalClass.getConstructor().newInstance();
        assertThrows(InvalidAnimalBirthDateException.class, () -> searchService.checkLeapYearAnimal(animal));
    }

    @Test
    void testCheckLeapYearAnimal_PositiveCase() {
        var animal = new Cat("Home", "Cat " + UUID.randomUUID(), 4300.573, "Angry Cat");
        animal.setBirthDate(formatBirthDate("22.03.2023"));
        try {
            String result = searchService.checkLeapYearAnimal(animal);
            assertEquals("Cat не был рожден в високосный год", result);
        } catch (InvalidAnimalException | InvalidAnimalBirthDateException e) {
            fail("Неожиданное исключение");
        }
    }

    @Test
    void testCheckLeapYearAnimal_InvalidAnimalException() {
        try {
            searchService.checkLeapYearAnimal(null);
            fail("Не получено ожидаемое исключение InvalidAnimalException");
        } catch (InvalidAnimalException e) {
            assertEquals("На вход пришел некорректный объект животного!", e.getMessage().substring(0, 45));
        } catch (InvalidAnimalBirthDateException e) {
            fail("Получено не ожидаемое исключение InvalidAnimalBirthDateException");
        }
    }

    @Test
    void testCheckLeapYearAnimal_InvalidAnimalBirthDateException() {
        var animal = new Tiger("Jungle", "Tiger " + UUID.randomUUID(), 300.573, "Evil Tiger");
        try {
            searchService.checkLeapYearAnimal(animal);
            fail("Не получено ожидаемое исключение InvalidAnimalBirthDateException");
        } catch (InvalidAnimalException e) {
            fail("Получено не ожидаемое исключение InvalidAnimalException");
        } catch (InvalidAnimalBirthDateException e) {
            assertEquals("У животного Tiger не указана дата его рождения!", e.getMessage());
        }
    }
}