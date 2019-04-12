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
        System.out.print("Случайно выбранные буквы: ");
        for (int i = 0; i < countOfWords - 2; ++i) {
            System.out.print(selectedWord[i] + " ");
        }
        System.out.println();
        System.out.println("Отдельно выбранные буквы: " + selectedWord[countOfWords - 2] + ", " + selectedWord[countOfWords - 1]);
        int range = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите расстояние: ");
            range = scanner.nextInt();
        }
        initializeDict();
        Set<String> wordsSet = CombinationGenerator.generate(selectedWord);
        List<String> matchedWords = searchCoincidence(wordsSet, range, selectedWord[countOfWords - 1], selectedWord[countOfWords - 2]);
        printMaxWords(matchedWords);
    }

    private static void initializeDict() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("dictionary.txt"), StandardCharsets.UTF_8))) {
            String string = null;
            while ((string = reader.readLine()) != null) {
                dict.add(string);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> searchCoincidence(Set<String> wordsSet, final int range, final char x, final char y) {
        List<String> allMatchedWords = new ArrayList<>();
        int count = 1;
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
                    if (dict.contains(newWord) && entersRange(newWord, x, y, range) && !allMatchedWords.contains(newWord)) {
                        System.out.println(count + ": " + newWord);
                        allMatchedWords.add(newWord);
                        ++count;
                    }
                }
            } else if (dict.contains(word) && entersRange(word, x, y, range) && !allMatchedWords.contains(word)) {
                System.out.println(count + ": " + word);
                allMatchedWords.add(word);
                ++count;
            }
        }
        return allMatchedWords;
    }

    private static boolean entersRange(String word, char x, char y, int range) {
        List<Integer> countX = new ArrayList<>();
        List<Integer> countY = new ArrayList<>();
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (c == x) {
                countX.add(i);
            } else if (c == y) {
                countY.add(i);
            }
        }
        for (var a : countX) {
            for (var b : countY) {
                if (Math.abs(a - b) == range - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printMaxWords(List<String> matchedWords) {
        List<String> strings = new ArrayList<>();
        String maxWord = null;
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