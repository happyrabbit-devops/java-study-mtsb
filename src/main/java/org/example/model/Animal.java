package org.example.model;

import java.time.LocalDate;
import java.time.Period;

import static org.example.utils.TextFileUtils.readFromFile;

public interface Animal {

    String SECRET_FILE_PATH = "src/main/resources/secretStore/secretInformation.txt";

    Habitat getHabitat();
    String getName();
    double getCost();
    String getCharacter();
    LocalDate getBirthDate();
    void setBirthDate(LocalDate birthDate);

    int getId();

    default String getSecretInformation() {
        return readFromFile(SECRET_FILE_PATH, getId());
    }

    default int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(getBirthDate(), currentDate);
        return period.getYears();
    }

    default String getInstanceName() {
        return String.format("%s %s %.2f %s",
                getHabitat().getName(),
                getName(),
                getCost(),
                getBirthDate().toString());
    }
}
