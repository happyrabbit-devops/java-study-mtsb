package org.example.model;

import java.time.LocalDate;
import java.time.Period;

public interface Animal {
    String getBreed();
    String getName();
    double getCost();
    String getCharacter();
    LocalDate getBirthDate();
    void setBirthDate(LocalDate birthDate);

    default int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(getBirthDate(), currentDate);
        return period.getYears();
    }
}
