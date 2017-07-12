package com.example.kata.numerology;

import java.util.List;
import java.util.stream.IntStream;

public class RuleReplace2ForAnEqualAmountOfNumbersToTheLeft {
    void applyRule2(Elements elements, int i, List<Integer> result) {
        Integer previousNumber = elements.previousOf(i);
        IntStream.rangeClosed(1, previousNumber).forEach((x) -> result.add(1));
    }
}
