package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CombinationGenerator {
    private static ArrayList<String> arrList = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();

    public static Set<String> generate(final char[] s) {
        for (int i = 2; i < s.length + 1; i ++)
            permuteIteration(s, 0, i);
        HashSet<String> wordsSet = arrList.stream().filter(x -> x.contains(Character.toString(s[s.length - 1]))).collect(Collectors.toCollection(HashSet::new));
        arrList = null;
        System.gc();
        return wordsSet;
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
