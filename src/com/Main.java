package com;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public  class Main {
    private static Map<Character, CostAndNumber> words = Words.map;
    private static HashSet<String> dict = new HashSet<>();

    public static void main(String[] args) {
        final int countOfWords = 9;
        char[] selectedWord = ChipsChooser.choose(countOfWords);
        //char[] selectedWord = { 'М', 'А', 'А', 'Г', 'Р', 'П', 'О', 'Р', 'М' };
        System.out.print("Случайно выбранные буквы: ");
        for (int i = 0; i < countOfWords - 2; ++i) {
            System.out.print(selectedWord[i] + " ");
        }
        System.out.println();
        System.out.println("Отдельно выбранные буквы: " + selectedWord[countOfWords - 2] + " " + selectedWord[countOfWords - 1]);
        int range = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите расстояние: ");
            range = scanner.nextInt();
        }
        initializeDict();
        Set<String> wordsSet = CombinationGenerator.generate(selectedWord, range, selectedWord[countOfWords - 2], selectedWord[countOfWords - 1]);
        List<String> matchedWords = searchCoincidence(wordsSet);
        printWords(matchedWords);
    }

    private static void initializeDict() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("dictionary.txt"), StandardCharsets.UTF_8))) {
            String word = null;
            while ((word = reader.readLine()) != null) {
                dict.add(word);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> searchCoincidence(Set<String> wordsSet) {
        List<String> allMatchedWords = new ArrayList<>();
        for (var word : wordsSet) {
            if (word.contains("*")) {
                String[] alphabet = new String[32];
                int index = 0;
                for (var bucket : words.entrySet()) {
                    if (bucket.getKey() == '*') {
                        continue;
                    }
                    alphabet[index++] = word.replace('*', bucket.getKey());
                }
                for (var newWord : alphabet) {
                    if (dict.contains(newWord) && !allMatchedWords.contains(newWord)) {
                        allMatchedWords.add(newWord);
                    }
                }
            } else if (dict.contains(word) && !allMatchedWords.contains(word)) {
                allMatchedWords.add(word);
            }
        }
        return allMatchedWords;
    }

    private static void printWords(List<String> matchedWords) {
        List<String> strings = new ArrayList<>();
        String maxWord = null;
        int count = 1;
        int maxValue = 0;
        for (String word : matchedWords) {
            char[] charWord = word.toCharArray();
            int value = 0;
            for (char c : charWord) {
                value += words.get(c).getCost();
            }
            if (value > maxValue) {
                strings.clear();
                maxValue = value;
                maxWord = word;
                strings.add(maxWord);
            } else if (value == maxValue) {
                strings.add(word);
            }
            System.out.println(count + ": " + word + " - " + value);
            ++count;
        }
        if (strings.size() == 1) {
            System.out.println("Слово " + strings.get(0) + " с максимальным значением " + maxValue);
        } else if (strings.size() > 1) {
            System.out.println("Слова " + strings + " с максимальным значением " + maxValue);
        } else {
            System.out.println("Невозможно составить слово из заданной последовательности");
        }
    }
}