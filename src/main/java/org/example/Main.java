package org.example;

import org.example.service.CreateAnimalServiceImpl;

public class Main {
    public static void main(String[] args) {
        var createAnimalService = new CreateAnimalServiceImpl();
        createAnimalService.createAnimals();
        createAnimalService.createAnimals(3);
        createAnimalService.createAnimalsWithDoWhileLoop(5);
    }
}