package org.example.service;

import org.example.exception.InvalidAnimalBirthDateException;
import org.example.exception.InvalidAnimalException;
import org.example.model.Animal;
import org.example.model.Elephant;
import org.example.model.Lynx;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        Animal animal = (Animal) animalClass.getConstructor().newInstance();
        assertThrows(InvalidAnimalBirthDateException.class, () -> searchService.checkLeapYearAnimal(animal));
    }
}