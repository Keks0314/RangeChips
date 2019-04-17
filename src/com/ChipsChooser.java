package com;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ChipsChooser {
    private static Map<Character, AmountAndCost> letters = Letters.container;
    private static ThreadLocalRandom randomGenerator = ThreadLocalRandom.current();

    public static char[] choose(final int count) {
        char[] chips = new char[count];
        for (int i = 0; i < count; ++i) {
            char letter = getRandomLetter();
            chips[i] = letter;
        }
        return chips;
    }

    private static char getRandomLetter() {
        char word = 'А';
        int choice = randomGenerator.nextInt(1, 132);
        while (choice > 0) {
            if (word == 'а') {
                return '*';
            }
            choice -= letters.get(word).getAmount();
            ++word;
        }
        return --word;
    }
}