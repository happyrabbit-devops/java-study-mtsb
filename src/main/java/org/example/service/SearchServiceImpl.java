package org.example.service;

import org.example.exception.InvalidAnimalBirthDateException;
import org.example.exception.InvalidAnimalException;
import org.example.model.Animal;

import java.time.LocalDate;

public class SearchServiceImpl implements SearchService {

    private static final String INVALID_ANIMAL_CLASS = "На вход пришел некорректный объект животного!";
    private static final String EMPTY_BIRTH_DATE = "У животного %s не указана дата его рождения!";
    private static final String LEAP_YEAR = "%s был рожден в високосный год";
    private static final String NOT_LEAP_YEAR = "%s не был рожден в високосный год";

    public String checkLeapYearAnimal(Animal animal) throws InvalidAnimalException, InvalidAnimalBirthDateException {
        if (animal == null) {
            throw new InvalidAnimalException(INVALID_ANIMAL_CLASS);
        }
        LocalDate birthDate = animal.getBirthDate();
        if (birthDate == null) {
            throw new InvalidAnimalBirthDateException(String.format(EMPTY_BIRTH_DATE, animal.getClass().getSimpleName()));
        }
        String result;
        if (birthDate.isLeapYear()) {
            result = String.format(LEAP_YEAR, animal.getClass().getSimpleName());
        } else {
            result = String.format(NOT_LEAP_YEAR, animal.getClass().getSimpleName());
        }
        return result;
    }
}
