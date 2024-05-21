package org.example.service;

import org.example.exception.InvalidAnimalBirthDateException;
import org.example.model.Animal;

/**
 * Сервис поиска.
 */
public interface SearchService {

    /**
     * Проверяет, является ли год рождения животного високосным.
     *
     * @param animal животное, для которого нужно выполнить проверку
     * @return сообщение о том, является ли год рождения високосным или нет
     * @throws InvalidAnimalBirthDateException если дата рождения животного недопустима
     */
    String checkLeapYearAnimal(Animal animal) throws InvalidAnimalBirthDateException;
}
