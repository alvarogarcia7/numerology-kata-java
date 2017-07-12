package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Rule9ForTwo10s {
    public Optional<List<Integer>> apply(Elements elements, int index) {
        Integer integer = elements.at(index);
        if (integer.equals(9)) {
            return Optional.of(Arrays.asList(10, 10));
        }
        return Optional.empty();
    }
}
