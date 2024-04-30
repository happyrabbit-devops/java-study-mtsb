package org.example.model;

import lombok.Getter;

@Getter
public enum AnimalNames {

    ELEPHANT("Слон"),
    LION("Лев"),
    CAT("Кошка"),
    TIGER("Тигр"),
    LYNX("Рысь"),
    WHALE("Кит");

    private final String name;

    AnimalNames(String name) {
        this.name = name;
    }

}
