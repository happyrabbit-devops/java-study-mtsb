package org.parallel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParallelTest {
    @Test
    void parallelCounterTest() {
        assertEquals(500000, ParallelCounter.execute());
    }
    @Test
    void textSearchTest_Chapter() {
        assertEquals(432, TextSearch.execute("CHAPTER"));
    }
    @Test
    void textSearchTest_VanillaElectronic() {
        assertEquals(2, TextSearch.execute("Vanilla Electronic"));
    }
    @Test
    void factorialTest_Ten() {
        assertEquals(3628800, Factorial.execute(10));
    }
    @Test
    void factorialTest_Twelve() {
        assertEquals(479001600, Factorial.execute(12));
    }
}