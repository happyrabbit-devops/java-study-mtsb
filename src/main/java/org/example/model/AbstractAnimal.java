package org.example.model;

import java.time.LocalDate;
import java.time.Period;

public abstract class AbstractAnimal implements Animal {
    protected String breed;
    protected String name;
    protected Double cost;
    protected String character;
    protected LocalDate birthDate;
}
