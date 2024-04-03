package org.example.service;

import org.example.storage.AnimalStorage;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    @Override
    public void createAnimals(int n, AnimalStorage storage) {
        for (int i = 0; i < n; i++) {
            storage.getAnimals().add(createAnimal(i));
        }
    }

    @Override
    public void createAnimalsWithDoWhileLoop(int n, AnimalStorage storage) {
        int i = 0;
        do {
            storage.getAnimals().add(createAnimal(i));
            i++;
        } while (i < n);
    }
}
