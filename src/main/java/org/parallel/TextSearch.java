package org.parallel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class TextSearch {

    static Logger logger = Logger.getLogger(TextSearch.class.getName());
    
    private static final String TEXT_LOCATION = "https://raw.githubusercontent.com/dscape/spell/master/test/resources/big.txt";
    private static final String SEARCH_SUBSTR = "CHAPTER";
    private static final int THREADS_COUNT = 6;

    public static void main(String[] args) {
        var logInfo = String.format("Число вхождений подстроки '%s': %d", SEARCH_SUBSTR, execute(SEARCH_SUBSTR));
        logger.info(logInfo);
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

    private static String downloadTextFromURL() {

        var stringBuilder = new StringBuilder();

        try {
            var url = new URL(TextSearch.TEXT_LOCATION);
            var connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
        return stringBuilder.toString();

    }

    public static int execute(String substring) {

        var text = downloadTextFromURL();
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
                logger.warning(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        executor.shutdown();
        return totalCount;
    }

}
