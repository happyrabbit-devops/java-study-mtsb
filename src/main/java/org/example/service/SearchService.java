package org.example.service;

import org.example.exception.InvalidAnimalBirthDateException;
import org.example.model.Animal;

public interface SearchService {
    String checkLeapYearAnimal(Animal animal) throws InvalidAnimalBirthDateException;
}
