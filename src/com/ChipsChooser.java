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
            while (letters.get(letter).getAmount() == 0) {
                letter = getRandomLetter();
            }
            chips[i] = letter;
            AmountAndCost updateState = letters.get(letter);
            updateState.setAmount(updateState.getAmount() - 1);
            letters.put(letter, updateState);
        }
        return chips;
    }

    private static char getRandomLetter() {
        char word = (char) (randomGenerator.nextInt(0, 33) + 'А');
        if (word == 32 + 'А') {
            word = '*';
        }
        return word;
    }
}