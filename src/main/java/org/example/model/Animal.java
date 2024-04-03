package org.example.model;

import java.time.LocalDate;

public interface Animal {
    String getBreed();
    String getName();
    double getCost();
    String getCharacter();
    LocalDate getBirthDate();
    void setBirthDate(LocalDate birthDate);
}
