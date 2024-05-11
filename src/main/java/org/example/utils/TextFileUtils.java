package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import static org.example.repository.AnimalRepositoryImpl.resourceLoader;
import static org.example.utils.Const.EMPTY_STR;
import static org.example.utils.Const.SPACE;

@Slf4j
public class TextFileUtils {

    private TextFileUtils() {
        //
    }

    public static <T> T downloadTextFromURL(String location, Class<T> returnType) {
        try {
            var url = new URL(location);
            var connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            if (returnType == String.class) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                reader.close();
                return returnType.cast(stringBuilder.toString());
            } else if (returnType == List.class) {
                List<String> lines = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();
                return returnType.cast(lines);
            } else {
                throw new IllegalArgumentException("Неподдерживаемый тип");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static String readFromFile(String filePath) {
        var path = Paths.get(filePath);
        try {
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error(e.getMessage());
            return EMPTY_STR;
        }
    }

    public static String readFromFile(String filePath, long lineIndex) {
        var path = Paths.get(filePath);
        try (var lines = Files.lines(path)) {
            return lines.skip(lineIndex)
                    .findFirst()
                    .orElseThrow(() -> new IOException("Не найдена секретная информация по индексу: " + lineIndex));
        } catch (IOException e) {
            log.error(e.getMessage());
            return EMPTY_STR;
        }
    }

    public static void writeToLog(String logStr) {
        var logFilePath = Paths.get(resourceLoader.getFilePath("animals/logData.txt"));
        try (var lines = Files.lines(logFilePath, StandardCharsets.UTF_8)) {
            Files.createDirectories(logFilePath.getParent());
            if (!Files.exists(logFilePath)) {
                Files.createFile(logFilePath);
            }
            int lineNumber = (int) lines.count();
            try (var writer = Files.newBufferedWriter(logFilePath, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
                writer.write(String.format("%d %s%n", lineNumber + 1, logStr));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static <T> void writeToJson(T result, String outputPath) {
        var objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            var logFilePath = Paths.get(outputPath);
            Files.createDirectories(logFilePath.getParent());
            objectMapper.writeValue(new File(outputPath), result);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private static final Gson gson = new Gson();

    public static <T> T readResultFromFile(Class<T> type, String filePath) {
        try {
            var jsonString = Files.readString(Paths.get(filePath));
            return gson.fromJson(jsonString, type);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }


    public static void readAndDecodeValues(String filePath) {
        Map<String, Integer> data = readResultFromFile(Map.class, filePath);
        assert data != null;
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String[] parts = entry.getKey().split(SPACE);
            var builder = new StringBuilder();
            for (int i = 0; i < parts.length; i++) {
                if (i == 5) {
                    builder.append(new String(Base64.getDecoder().decode(parts[i])));
                } else {
                    builder.append(parts[i]).append(SPACE);
                }
            }
            var newKey = builder.toString().trim();
            log.info(String.format("findOlderAnimal %s, возраст: %s", newKey, entry.getValue()));
        }
    }

    public static long countLines(String filePath) {
        var path = Paths.get(filePath);
        try (var lines = Files.lines(path)){
            return lines.count();
        } catch (IOException e) {
            log.error(e.getMessage());
            return -1;
        }
    }

}
