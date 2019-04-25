package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationsGenerator {
    private List<String> wordCombinations = new ArrayList<>();
    private StringBuilder letterCombination = new StringBuilder();
    private final char[] letters;
    private final int range;
    private final char x;
    private final char y;

    public CombinationsGenerator(final char[] letters, final int range, char x, char y) {
        this.letters = letters;
        this.range = range;
        this.x = x;
        this.y = y;
    }

    public Set<String> generate() {
        for (int i = 2; i < letters.length + 1; ++i) {
            permuteIteration(letters, 0, i);
        }
        letterCombination = null;
        return getSetWithMandatoryLetters(wordCombinations);
    }

    private Set<String> getSetWithMandatoryLetters(List<String> wordCombinations) {
        Set<String> wordsSet = new HashSet<>();
        String stringX = String.valueOf(x);
        String stringY = String.valueOf(y);
        if (x == '*' && y == '*') {
            wordsSet.addAll(wordCombinations);
        } else if (x == '*') {
            for (var word : wordCombinations) {
                if (word.contains(stringY)) {
                    wordsSet.add(word);
                }
            }
        } else if (y == '*') {
            for (var word : wordCombinations) {
                if (word.contains(stringX)) {
                    wordsSet.add(word);
                }
            }
        } else {
            for (var word : wordCombinations) {
                if (word.contains(String.valueOf(x))
                        && word.contains(String.valueOf(y))) {
                    wordsSet.add(word);
                }
            }
        }
        wordCombinations = null;
        return wordsSet;
    }

    private void permuteIteration(char[] letters, int index, int limit){
        if (index >= limit) {
            for (int i = 0; i < limit; ++i) {
                letterCombination.append(letters[i]);
            }
            checkWord(letterCombination.toString());
            letterCombination.delete(0, letterCombination.length());
            return;
        }

        for (int i = index; i < letters.length; ++i) {
            swapLetter(letters, index, i);
            permuteIteration(letters, index + 1, limit);
            swapLetter(letters, index, i);
        }
    }

    private void checkWord(String word) {
        if (word.contains("*")) {
            List<String> wordsWithoutStars = getWordsWithoutStars(word);
            for (var w : wordsWithoutStars) {
                if (entersRange(w, range, x, y)) {
                    wordCombinations.add(w);
                }
            }
        } else if (entersRange(word, range, x, y)) {
            wordCombinations.add(word);
        }
    }

    private List<String> getWordsWithoutStars(String word) {
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

    private List<String> replaceStar(String word) {
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

    private boolean entersRange(String word, int range, char x, char y) {
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

    private void swapLetter(char[] letters, int first, int second) {
        char tmp = letters[first];
        letters[first] = letters[second];
        letters[second] = tmp;
    }
}