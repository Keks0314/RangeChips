package com;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordsChecker {
    private static Set<String> dictionary = new HashSet<>(297510);

    public static List<String> check(char[] word, int range, char x, char y) {
        initializeDictionary();
        Set<String> wordsSet = new CombinationsGenerator(word, range, x, y).generate();
        return searchCoincidence(wordsSet);
    }

    private static void initializeDictionary() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("dictionary.txt"), StandardCharsets.UTF_8))) {
            String word = null;
            while ((word = reader.readLine()) != null) {
                dictionary.add(word);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> searchCoincidence(Set<String> wordsSet) {
        List<String> allMatchedWords = new ArrayList<>();
        for (var word : wordsSet) {
            if (dictionary.contains(word) && !allMatchedWords.contains(word)) {
                allMatchedWords.add(word);
            }
        }
        return allMatchedWords;
    }
}