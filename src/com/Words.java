package com;

import java.util.HashMap;
import java.util.Map;

public class Words {
    public static Map<Character, CostAndNumber> map = new HashMap<>(33) {{
        put('А', new CostAndNumber(10, 1));
        put('Б', new CostAndNumber(3, 3));
        put('В', new CostAndNumber(5, 2));
        put('Г', new CostAndNumber(3, 3));
        put('Д', new CostAndNumber(5, 2));
        put('Е', new CostAndNumber(9, 1));
        put('Ж', new CostAndNumber(2, 5));
        put('З', new CostAndNumber(2, 5));
        put('И', new CostAndNumber(8, 1));
        put('Й', new CostAndNumber(4, 2));
        put('К', new CostAndNumber(6, 2));
        put('Л', new CostAndNumber(4, 2));
        put('М', new CostAndNumber(5, 2));
        put('Н', new CostAndNumber(8, 1));
        put('О', new CostAndNumber(10, 1));
        put('П', new CostAndNumber(6, 2));
        put('Р', new CostAndNumber(6, 2));
        put('С', new CostAndNumber(6, 2));
        put('Т', new CostAndNumber(5, 2));
        put('У', new CostAndNumber(3, 3));
        put('Ф', new CostAndNumber(1, 10));
        put('Х', new CostAndNumber(2, 5));
        put('Ц', new CostAndNumber(1, 10));
        put('Ч', new CostAndNumber(2, 5));
        put('Ш', new CostAndNumber(1, 10));
        put('Щ', new CostAndNumber(1, 10));
        put('Ъ', new CostAndNumber(1, 10));
        put('Ы', new CostAndNumber(2, 5));
        put('Ь', new CostAndNumber(2, 5));
        put('Э', new CostAndNumber(1, 10));
        put('Ю', new CostAndNumber(1, 10));
        put('Я', new CostAndNumber(3, 3));
        put('*', new CostAndNumber(3, 0));
    }};
}