package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CombinationGenerator {
    private static ArrayList<String> arrList = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();

    public static Set<String> generate(final char[] s, int range, char x, char y) {
        for (int i = 2; i < s.length + 1; i ++)
            permuteIteration(s, 0, i);
        Set<String> wordsSet = getCorrectSet(arrList, s, range, x, y);
        arrList = null;
        System.gc();
        return wordsSet;
    }

    private static Set<String> getCorrectSet(List<String> arrList, char[] s, int range, char x, char y) {
        Set<String> wordsSet = new HashSet<>();
        for (var word : arrList) {
            if (word.contains(Character.toString(s[s.length - 1]))
                    && word.contains(Character.toString(s[s.length - 2]))
                    && entersRange(word, x, y, range)) {
                wordsSet.add(word);
            }
        }
        return wordsSet;
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
                if (b - a - 1 == range || a - b - 1 == range) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void permuteIteration(char[] arr, int index, int limit){
        if(index >= limit){
            for(int i = 0; i < limit; i++) sb.append(arr[i]);
            arrList.add(sb.toString());
            sb.delete(0, sb.length());
            return;
        }

        for(int i = index; i < arr.length; i++){
            char temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;

            permuteIteration(arr, index+1, limit);

            temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }
}
