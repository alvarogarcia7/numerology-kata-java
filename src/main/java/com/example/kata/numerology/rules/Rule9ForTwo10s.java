package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import io.vavr.control.Option;

import java.util.Arrays;
import java.util.List;

public class Rule9ForTwo10s implements Rule {
    public Option<List<Integer>> apply(Elements elements, int index) {
        if (!elements.exists(index)) {
            return Option.none();
        }
        Integer integer = elements.at(index);
        if (integer.equals(9)) {
            return Option.of(Arrays.asList(10, 10));
        }
        return Option.none();
    }
}
