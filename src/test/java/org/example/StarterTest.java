package org.example;

import org.example.model.Animal;
import org.example.repository.AnimalRepositoryImpl;
import org.example.service.CreateAnimalServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class StarterTest {

    @Value("${arrayOfStrings}")
    private String[] catNamesArray;

    @Test
    void testCatNamesArray() {
        assertArrayEquals(new String[]{"Barsik", "Persik", "Murzik"}, catNamesArray);
    }

    @Autowired
    private AnimalRepositoryImpl animalRepository;

    @Autowired
    private CreateAnimalServiceImpl createAnimalService;

    @Test
    void testAnimalRepositoryInitialization() {
        Map<String, List<Animal>> storage = animalRepository.getStorage().getAnimals();
        assertNotNull(storage);
        assertEquals(100, storage.values().stream()
                .mapToInt(List::size)
                .sum());
    }

    @Test
    void testCreateAnimalServiceInitialization() {
        assertNotNull(createAnimalService);
    }
}