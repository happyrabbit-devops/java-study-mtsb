package org.parallel;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ParallelCounter {

    private static final int TARGET_LIMIT = 500000;
    private static final int THREADS_COUNT = 4;

    private final AtomicInteger data;

    public ParallelCounter() {
        this.data = new AtomicInteger(0);
    }

    public void increment() {
        data.incrementAndGet();
    }

    public int getCount() {
        return data.get();
    }

    public static int execute() {

        var counter = new ParallelCounter();

        var threads = new Thread[THREADS_COUNT];

        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < TARGET_LIMIT / THREADS_COUNT; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        return counter.getCount();
    }

    public static void main(String[] args) {
        log.info(String.format("Значение счетчика: %d", execute()));
    }

}
