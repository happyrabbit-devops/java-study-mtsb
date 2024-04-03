package org.example.service;

import org.example.model.*;
import java.util.UUID;

public class AnimalFactory {
    AnimalFactory() {
        //
    }
    public static Animal createAnimal(String type) {
        return switch (type.toLowerCase()) {
            case "elephant" -> new Elephant("Indian", "Elephant " + UUID.randomUUID(), 5000.354, "Friendly");
            case "lion" -> new Lion("Safari", "Lion " + UUID.randomUUID(), 10000.573, "Angry Lion");
            case "cat" -> new Cat("Home", "Cat " + UUID.randomUUID(), 4300.573, "Angry Cat");
            case "tiger" -> new Tiger("Jungle", "Tiger " + UUID.randomUUID(), 300.573, "Evil Tiger");
            case "lynx" -> new Lynx("Forest", "Lynx " + UUID.randomUUID(), 2230.573, "Angry Lynx");
            case "whale" -> new Whale("Sea", "Whale " + UUID.randomUUID(), 2230777.573, "Peaceful Whale");
            default -> throw new IllegalArgumentException("Неизвестный тип: " + type);
        };
    }
}
