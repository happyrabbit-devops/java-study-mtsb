package org.example.storage;

import lombok.Data;
import org.example.model.Animal;

import java.util.ArrayList;
import java.util.List;

@Data
public class AnimalStorage {
    private List<Animal> animals = new ArrayList<>();
}
