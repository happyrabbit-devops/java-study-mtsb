package org.example.cg.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.InvalidAnimalBirthDateException;
import org.example.exception.InvalidAnimalException;
import org.example.model.Animal;
import org.example.repository.AnimalRepositoryImpl;
import org.example.service.CreateAnimalServiceImpl;
import org.example.service.SearchServiceImpl;
import org.example.storage.AnimalStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;
import static org.example.repository.AnimalRepositoryImpl.resourceLoader;
import static org.example.utils.TextFileUtils.countLines;

@Service
@Slf4j
public class ZooCgServiceImpl implements ZooCgService {

    @Value("${CRON_DISABLED:false}")
    private boolean cronDisabled;

    private final SearchServiceImpl searchService;
    private final AnimalRepositoryImpl animalRepository;

    @Autowired
    public ZooCgServiceImpl(SearchServiceImpl searchService, AnimalRepositoryImpl animalRepository) {
        this.searchService = searchService;
        this.animalRepository = animalRepository;
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static LocalDate formatBirthDate(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }

    @Override
    public void processZoo() {

        var zoo1 = new AnimalStorage();
        var zoo2 = new AnimalStorage();
        var zoo3 = new AnimalStorage();
        var testZoo = new AnimalStorage();

        var createAnimalService = new CreateAnimalServiceImpl();

        zoo1.setAnimals(createAnimalService.createAnimals());
        zoo2.setAnimals(createAnimalService.createAnimals(33));
        zoo3.setAnimals(createAnimalService.createAnimalsWithDoWhileLoop(55));

        testZoo.setAnimals(createAnimalService.createAnimals(3));

        Map<String, List<Animal>> animalsMap = testZoo.getAnimals();

        List<String> keysList = new ArrayList<>(animalsMap.keySet());

        // Указываем дату рождения для первого животного
        animalsMap.get(keysList.get(0)).get(0).setBirthDate(formatBirthDate("22.03.1995"));

        // Если в карте больше 1 вида животного, то укажем дату рождения для первого во втором типе
        if (keysList.size() > 1) {
            animalsMap.get(keysList.get(1)).get(0).setBirthDate(formatBirthDate("11.04.2005"));
        } else {
            // иначе у нас все 3 животных одного вида, добавим дату рождения для второго по порядку
            animalsMap.get(keysList.get(0)).get(1).setBirthDate(formatBirthDate("11.04.2005"));
        }

        // Добавим нулевой объект для ошибки
        animalsMap.get(keysList.get(0)).add(null);

        for (Map.Entry<String, List<Animal>> entry : animalsMap.entrySet()) {
            if (nonNull(entry.getValue())) {
                for (var animal : entry.getValue()) {
                    try {
                        log.info(searchService.checkLeapYearAnimal(animal));
                    } catch (InvalidAnimalException | InvalidAnimalBirthDateException e) {
                        log.error(e.getMessage());
                    }
                }
            }
        }

        log.info(String.format("Число строк в лог-файле: %d",
                countLines(resourceLoader.getFilePath("animals/logData.txt"))));
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void animalRepositoryPeriod() {
        if (!cronDisabled) {
            log.info("Вызов функции раз в минуту: "+new Date());
            animalRepository.findOlderAnimal(30);
            log.info(String.format("Средний возраст кошек: %f.1 лет",
                    animalRepository.findAverageAge("Cat")));
            animalRepository.findDuplicate();
            animalRepository.findMinCostAnimals("Lion");
            animalRepository.findOldAndExpensive("Whale");
            log.info(String.format("Число строк в лог-файле: %d",
                    countLines(resourceLoader.getFilePath("animals/logData.txt"))));
        }
    }

}
