package org.example.service;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    @Override
    public void createAnimals(int n) {
        for (int i = 0; i < n; i++) {
            createAnimal(i);
        }
    }

    @Override
    public void createAnimalsWithDoWhileLoop(int n) {
        int i = 0;
        do {
            createAnimal(i);
            i++;
        } while (i < n);
    }
}
