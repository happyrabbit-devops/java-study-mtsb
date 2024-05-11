package org.example.cg.impl;

public interface ZooCgService {

    /**
     * @deprecated
     * Легаси с прошлой версии
     */
    @Deprecated
    void processZoo();

    /**
     * Периодические функции.
     */
    void animalRepositoryPeriod();
}
