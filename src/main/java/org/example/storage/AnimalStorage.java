package org.example.storage;

import lombok.Data;
import org.example.model.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class AnimalStorage {
    private Map<String, List<Animal>> animals;
}
