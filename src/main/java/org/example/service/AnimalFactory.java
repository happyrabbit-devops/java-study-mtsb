package org.example.service;

import org.example.model.*;

import java.util.List;
import java.util.Random;

import static java.util.Objects.nonNull;
import static org.example.model.AnimalNames.*;
import static org.example.model.Habitat.*;
import static org.example.service.CreateAnimalServiceImpl.FEMALE_NAMES_LOCATION;
import static org.example.service.CreateAnimalServiceImpl.MALE_NAMES_LOCATION;
import static org.example.utils.Const.*;
import static org.example.utils.DateUtils.getRandomBirthDate;
import static org.example.utils.TextFileUtils.*;

public class AnimalFactory {

    static Random random = new Random();

    protected static final List<String> MALE_LIST_NAMES = downloadTextFromURL(MALE_NAMES_LOCATION, List.class);
    protected static final List<String> FEMALE_LIST_NAMES = downloadTextFromURL(FEMALE_NAMES_LOCATION, List.class);

    private AnimalFactory() {
        //
    }

    public static String getRandomName(List<String> namesList) {
        if (nonNull(namesList)) {
            return namesList.get(random.nextInt(0, namesList.size() - 1));
        } else {
            return EMPTY_STR;
        }
    }

    public static Animal createAnimal(String type, int i) {

        return switch (type.toLowerCase()) {
            case "elephant" -> new Elephant(i, INDIAN, String.format(TWO_WORD, ELEPHANT.getName(), getRandomName(MALE_LIST_NAMES)), getRandomBirthDate(), random.nextDouble(2000, 6000), "Friendly");
            case "lion" -> new Lion(i, SAFARI, String.format(TWO_WORD, LION.getName(), getRandomName(MALE_LIST_NAMES)), getRandomBirthDate(), random.nextDouble(10000, 30000), "Angry Lion");
            case "cat" -> new Cat(i, HOME, String.format(TWO_WORD, CAT.getName(), getRandomName(FEMALE_LIST_NAMES)), getRandomBirthDate(), random.nextDouble(100, 1000), "Angry Cat");
            case "tiger" -> new Tiger(i, JUNGLE, String.format(TWO_WORD, TIGER.getName(), getRandomName(MALE_LIST_NAMES)), getRandomBirthDate(), random.nextDouble(20000, 60000), "Evil Tiger");
            case "lynx" -> new Lynx(i, FOREST, String.format(TWO_WORD, LYNX.getName(), getRandomName(FEMALE_LIST_NAMES)), getRandomBirthDate(), random.nextDouble(2000, 8000), "Angry Lynx");
            case "whale" -> new Whale(i, SEA, String.format(TWO_WORD, WHALE.getName(), getRandomName(MALE_LIST_NAMES)), getRandomBirthDate(), random.nextDouble(2000000, 6000000), "Peaceful Whale");
            default -> throw new IllegalArgumentException("Неизвестный тип: " + type);
        };
    }
}
