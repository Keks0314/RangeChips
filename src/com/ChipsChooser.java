package com;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ChipsChooser {
    private static Map<Character, CostAndNumber> words = Words.map;

    public static char[] choose(final int count) {
        char[] chips = new char[count];
        var random = ThreadLocalRandom.current();
        for (int i = 0; i < count; ++i) {
            char word = (char) (random.nextInt(0, 33) + 'А');
            if (word == 32 + 'А') {
                word = '*';
            }
            while (words.get(word).getNumber() == 0) {
                word = (char) (random.nextInt(0, 33) + 'А');
            }
            chips[i] = word;
            CostAndNumber last = words.get(word);
            last.setNumber(last.getNumber() - 1);
            words.put(word, last);
        }
        return chips;
    }
}