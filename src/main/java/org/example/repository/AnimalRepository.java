package org.example.repository;

import org.example.model.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Интерфейс для работы с хранилищем данных о животных.
 */
public interface AnimalRepository {

    /**
     * Находит имена животных, которые родились в високосные годы.
     *
     * @return словарь с именами животных и датами их рождения
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * Находит животных старше определенного возраста.
     *
     * @param age возраст для поиска
     * @return словарь с животными и их возрастом
     */
    Map<Animal, Integer> findOlderAnimal(int age);

    /**
     * Находит повторяющиеся имена животных и их количество.
     *
     * @return словарь с именами животных и количеством повторений
     */
    Map<String, Integer> findDuplicate();

    /**
     * Рассчитывает средний возраст животного по ключу.
     *
     * @param animalKey ключ для поиска животных
     * @return средний возраст животных
     */
    double findAverageAge(String animalKey);

    /**
     * Находит старых и дорогих животных по ключу.
     *
     * @param animalKey ключ для поиска животных
     * @return список животных, которые старые и дорогие
     */
    List<Animal> findOldAndExpensive(String animalKey);

    /**
     * Находит животных с минимальной стоимостью по ключу.
     *
     * @param animalKey ключ для поиска животных
     * @return список имен животных с минимальной стоимостью
     */
    List<String> findMinCostAnimals(String animalKey);
}


