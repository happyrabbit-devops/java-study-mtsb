package org.parallel;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class Factorial {

    private static final int THREADS_COUNT = 4;
    private static final int FACTORIAL_DIGIT = 10;

    private static long calculateFactorial(int start, int end) {
        long result = 1;
        for (int i = start; i <= end; i++) {
            result *= i;
        }
        log.info(String.format("Поток %s считает факториал от %d до %d = %d", Thread.currentThread().getName(), start, end, result));
        return result;
    }

    public static void main(String[] args) {
        log.info(String.format("Факториал числа %d равен %s", FACTORIAL_DIGIT, execute(FACTORIAL_DIGIT)));
    }

    public static long execute(int factorialDigit) {

        ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);

        int chunkSize = factorialDigit / THREADS_COUNT;

        List<CompletableFuture<Long>> futures = new ArrayList<>();
        for (int i = 0; i < factorialDigit; i += chunkSize) {
            int start = i + 1;
            int end = Math.min(i + chunkSize, factorialDigit);
            CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> calculateFactorial(start, end), executor);
            futures.add(future);
        }

        CompletableFuture<Long> finalResult = CompletableFuture.completedFuture(1L);
        for (CompletableFuture<Long> future : futures) {
            finalResult = finalResult.thenCombine(future, (result1, result2) -> result1 * result2);
        }

        try {
            return finalResult.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }

        executor.shutdown();
        return -1;

    }
}
