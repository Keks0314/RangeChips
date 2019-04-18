package com;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ChipsChooser {
    private static Map<Character, AmountAndCost> letters = Letters.container;
    private static ThreadLocalRandom randomGenerator = ThreadLocalRandom.current();
    private static int boundAmountOfChips = 132;

    public static char[] choose(final int count) {
        char[] chips = new char[count];
        for (int i = 0; i < count; ++i) {
            char letter = getRandomLetter();
            chips[i] = letter;
        }
        return chips;
    }

    private static char getRandomLetter() {
        char letter = 'А';
        int choice = randomGenerator.nextInt(1, boundAmountOfChips);
        --boundAmountOfChips;
        while (choice > 0) {
            if (letter == 'а') {
                updateLetterAmount('*', letters.get('*'));
                return '*';
            }
            choice -= letters.get(letter).getAmount();
            ++letter;
        }
        --letter;
        updateLetterAmount(letter, letters.get(letter));
        return letter;
    }

    private static void updateLetterAmount(char letter, AmountAndCost updateAmount) {
        updateAmount.setAmount(updateAmount.getAmount() - 1);
        letters.put(letter, updateAmount);
    }
}
