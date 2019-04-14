package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationsGenerator {
    private static List<String> wordCombinations = new ArrayList<>(986400);
    private static StringBuilder wordCombination = new StringBuilder();

    public static Set<String> generate(final char[] letters) {
        for (int i = 2; i < letters.length + 1; ++i) {
            permuteIteration(letters, 0, i);
        }
        wordCombination = null;
        return getSetWithMandatoryLetters(wordCombinations, letters);
    }

    private static Set<String> getSetWithMandatoryLetters(List<String> wordCombinations, char[] letters) {
        Set<String> wordsSet = new HashSet<>();
        for (var word : wordCombinations) {
            if (word.contains(String.valueOf(letters[letters.length - 1]))
                    && word.contains(String.valueOf(letters[letters.length - 2]))) {
                wordsSet.add(word);
            }
        }
        wordCombinations = null;
        return wordsSet;
    }

    private static void permuteIteration(char[] letters, int index, int limit){
        if (index >= limit) {
            for (int i = 0; i < limit; ++i) {
                wordCombination.append(letters[i]);
            }
            wordCombinations.add(wordCombination.toString());
            wordCombination.delete(0, wordCombination.length());
            return;
        }

        for (int i = index; i < letters.length; ++i) {
            swapLetter(letters, index, i);
            permuteIteration(letters, index + 1, limit);
            swapLetter(letters, index, i);
        }
    }

    private static void swapLetter(char[] letters, int first, int second) {
        char tmp = letters[first];
        letters[first] = letters[second];
        letters[second] = tmp;
    }
}