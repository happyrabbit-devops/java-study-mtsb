package org.example.model;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;

public abstract class AbstractAnimal implements Animal {
    protected Habitat habitat;
    protected String name;
    protected Double cost;
    protected String character;
    protected LocalDate birthDate;
    protected int id;
    @Override
    public String toString() {
        return String.format("%s %s %.2f %s %s",
                getHabitat().getName(),
                getName(),
                getCost(),
                getBirthDate().toString(),
                Base64.getEncoder().encodeToString(getSecretInformation().getBytes(StandardCharsets.UTF_8)));
    }
}
