package com;

import java.util.*;

public  class Main {
    private static Map<Character, CostAndNumber> words = Words.map;

    public static void main(String[] args) {
        final int countOfWords = 9;
        char[] selectedWord = ChipsChooser.choose(countOfWords);
        //char[] selectedWord = { 'М', 'А', 'А', 'Г', 'Р', 'П', 'О', 'Р', 'М' };
        System.out.print("Случайно выбранные буквы: ");
        for (int i = 0; i < countOfWords - 2; ++i) {
            System.out.print(selectedWord[i] + " ");
        }
        System.out.println("\nОтдельно выбранные буквы: " + selectedWord[countOfWords - 2] + " " + selectedWord[countOfWords - 1]);
        int range = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите расстояние: ");
            range = scanner.nextInt();
        }
        List<String> matchedWords = WordChecker.check(selectedWord, range, selectedWord[countOfWords - 2], selectedWord[countOfWords - 1]);
        Collections.sort(matchedWords);
        printWords(matchedWords);
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