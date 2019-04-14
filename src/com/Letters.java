package com;

import java.util.HashMap;
import java.util.Map;

public class Letters {
    public static Map<Character, AmountAndCost> container = new HashMap<>(33) {{
        put('А', new AmountAndCost(10, 1));
        put('Б', new AmountAndCost(3, 3));
        put('В', new AmountAndCost(5, 2));
        put('Г', new AmountAndCost(3, 3));
        put('Д', new AmountAndCost(5, 2));
        put('Е', new AmountAndCost(9, 1));
        put('Ж', new AmountAndCost(2, 5));
        put('З', new AmountAndCost(2, 5));
        put('И', new AmountAndCost(8, 1));
        put('Й', new AmountAndCost(4, 2));
        put('К', new AmountAndCost(6, 2));
        put('Л', new AmountAndCost(4, 2));
        put('М', new AmountAndCost(5, 2));
        put('Н', new AmountAndCost(8, 1));
        put('О', new AmountAndCost(10, 1));
        put('П', new AmountAndCost(6, 2));
        put('Р', new AmountAndCost(6, 2));
        put('С', new AmountAndCost(6, 2));
        put('Т', new AmountAndCost(5, 2));
        put('У', new AmountAndCost(3, 3));
        put('Ф', new AmountAndCost(1, 10));
        put('Х', new AmountAndCost(2, 5));
        put('Ц', new AmountAndCost(1, 10));
        put('Ч', new AmountAndCost(2, 5));
        put('Ш', new AmountAndCost(1, 10));
        put('Щ', new AmountAndCost(1, 10));
        put('Ъ', new AmountAndCost(1, 10));
        put('Ы', new AmountAndCost(2, 5));
        put('Ь', new AmountAndCost(2, 5));
        put('Э', new AmountAndCost(1, 10));
        put('Ю', new AmountAndCost(1, 10));
        put('Я', new AmountAndCost(3, 3));
        put('*', new AmountAndCost(3, 0));
    }};
}