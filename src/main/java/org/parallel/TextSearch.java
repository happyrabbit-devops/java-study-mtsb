package org.parallel;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.example.utils.TextFileUtils.downloadTextFromURL;

@Slf4j
public class TextSearch {

    private static final String TEXT_LOCATION = "https://raw.githubusercontent.com/dscape/spell/master/test/resources/big.txt";
    private static final String SEARCH_SUBSTR = "CHAPTER";
    private static final int THREADS_COUNT = 6;

    public static void main(String[] args) {
        var logInfo = String.format("Число вхождений подстроки '%s': %d", SEARCH_SUBSTR, execute(SEARCH_SUBSTR));
        log.info(logInfo);
    }

    private static Callable<Integer> getIntegerCallable(int i, int chunkSize, String text, String substring) {

        final int startIndex = i * chunkSize;
        final int endIndex = (i == THREADS_COUNT - 1) ? text.length() : (i + 1) * chunkSize;

        String chunk = text.substring(startIndex, endIndex);
        return () -> {
            int count = 0;
            int index = chunk.indexOf(substring);
            while (index != -1) {
                count++;
                index = chunk.indexOf(substring, index + 1);
            }
            return count;
        };

    }

    public static int execute(String substring) {

        var text = downloadTextFromURL(TextSearch.TEXT_LOCATION, String.class);
        assert text != null;
        int chunkSize = text.length() / THREADS_COUNT;
        var executor = Executors.newFixedThreadPool(THREADS_COUNT);
        List<Future<Integer>> results = new ArrayList<>();

        for (int i = 0; i < THREADS_COUNT; i++) {
            Callable<Integer> task = getIntegerCallable(i, chunkSize, text, substring);
            results.add(executor.submit(task));
        }
        int totalCount = 0;
        for (Future<Integer> result : results) {
            try {
                totalCount += result.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        executor.shutdown();
        return totalCount;
    }

}
