package org.example.storage;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Animal;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class AnimalStorage {
    private Map<String, List<Animal>> animals;

    public AnimalStorage(Map<String, List<Animal>> animals) {
        this.animals = animals;
    }
}
