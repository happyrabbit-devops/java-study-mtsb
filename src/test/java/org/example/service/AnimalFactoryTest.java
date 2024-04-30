package org.example.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.service.AnimalFactory.getRandomName;
import static org.example.service.CreateAnimalServiceImpl.MALE_NAMES_LOCATION;
import static org.example.utils.Const.EMPTY_STR;
import static org.example.utils.TextFileUtils.downloadTextFromURL;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AnimalFactoryTest {

    @Test
    void getRandomName_Test() {
        assertNotEquals(EMPTY_STR, getRandomName(downloadTextFromURL(MALE_NAMES_LOCATION, List.class)));
    }
}