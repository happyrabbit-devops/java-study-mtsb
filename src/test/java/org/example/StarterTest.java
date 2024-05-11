package org.example;

import org.example.service.CreateAnimalServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StarterTest {

    @Autowired
    private CreateAnimalServiceImpl animalCreationService;
}
