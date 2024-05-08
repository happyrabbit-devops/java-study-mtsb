package org.example.model;

import lombok.Getter;

@Getter
public enum Habitat {

    INDIAN("Индийский"),
    SAFARI("Сафари"),
    HOME("Домашний"),
    JUNGLE("Джунгли"),
    FOREST("Лес"),
    SEA("Море");

    private final String name;

    Habitat(String name) {
        this.name = name;
    }

}
