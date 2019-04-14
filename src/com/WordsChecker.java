package com;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordsChecker {
    private static Set<String> dictionary = new HashSet<>(297510);

    public static List<String> check(char[] word, int range, char x, char y) {
        initializeDictionary();
        Set<String> wordsSet = CombinationsGenerator.generate(word);
        return searchCoincidence(wordsSet, range, x, y);
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

    private static List<String> searchCoincidence(Set<String> wordsSet, int range, char x, char y) {
        List<String> allMatchedWords = new ArrayList<>();
        for (var word : wordsSet) {
            if (word.contains("*")) {
                List<String> wordsWithoutStar = getWordWithoutStars(word);
                for (var newWord : wordsWithoutStar) {
                    if (dictionary.contains(newWord) && entersRange(newWord, range, x, y) && !allMatchedWords.contains(newWord)) {
                        allMatchedWords.add(newWord);
                    }
                }
            } else if (dictionary.contains(word) && entersRange(word, range, x, y) && !allMatchedWords.contains(word)) {
                allMatchedWords.add(word);
            }
        }
        return allMatchedWords;
    }

    private static boolean entersRange(String word, int range, char x, char y) {
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
        if (x == '*' && y == '*') {
            return true;
        } else if (x == '*') {
            for (var index : countY) {
                if ((index + range + 1) < word.length() || index - range - 1 >= 0) {
                    return true;
                }
            }
            return false;
        } else if (y == '*') {
            for (var index : countX) {
                if ((index + range + 1) < word.length() || index - range - 1 >= 0) {
                    return true;
                }
            }
            return false;
        }
        for (var a : countX) {
            for (var b : countY) {
                if (Math.abs(b - a) - 1 == range) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<String> getWordWithoutStars(String word) {
        List<String> wordsWithoutStar = new ArrayList<>();
        List<String> wordsWithoutOneStar = replaceStar(word);
        List<String> wordsWithoutTwoStar = null;
        List<String> wordsWithoutThreeStar = null;
        int countOfStars = (int) word.chars().mapToObj(x -> (char) x).filter(x -> x == '*').count();
        if (countOfStars > 1) {
            wordsWithoutTwoStar = new ArrayList<>();
            for (var wordWithoutOneStar : wordsWithoutOneStar) {
                wordsWithoutTwoStar.addAll(replaceStar(wordWithoutOneStar));
            }
            if (countOfStars > 2) {
                wordsWithoutThreeStar = new ArrayList<>();
                for (var wordWithoutTwoStar : wordsWithoutTwoStar) {
                    wordsWithoutThreeStar.addAll(replaceStar(wordWithoutTwoStar));
                }
                wordsWithoutStar.addAll(wordsWithoutThreeStar);
            } else {
                wordsWithoutStar.addAll(wordsWithoutTwoStar);
            }
        } else {
            wordsWithoutStar.addAll(wordsWithoutOneStar);
        }
        return wordsWithoutStar;
    }

    private static List<String> replaceStar(String word) {
        List<String> listWithReplacedStarSymbol = new ArrayList<>();
        String alphabet = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        final int starIndex = word.indexOf("*");
        for (int i = 0; i < alphabet.length(); ++i) {
            StringBuilder newWord = new StringBuilder();
            newWord.append(word, 0, starIndex)
                    .append(alphabet.charAt(i))
                    .append(word, starIndex + 1, word.length());
            listWithReplacedStarSymbol.add(newWord.toString());
        }
        return listWithReplacedStarSymbol;
    }
}