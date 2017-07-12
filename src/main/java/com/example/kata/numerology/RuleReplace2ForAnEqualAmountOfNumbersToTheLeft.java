package com.example.kata.numerology;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class RuleReplace2ForAnEqualAmountOfNumbersToTheLeft {
    void applyRule2(Elements elements, int i, List<Integer> result) {
        x(elements, i).forEach(x1->result.add(x1));
    }

    private List<Integer> x(Elements elements, int i) {
        Integer previousNumber = elements.previousOf(i);
        return IntStream.rangeClosed(1, previousNumber).map((x) -> 1).boxed().collect(toList());
    }

    public Optional<List<Integer>> apply(Elements elements, int index) {
        Integer integer = elements.at(index);
        if (integer.equals(2) && elements.exists(index - 1)) {
            return Optional.of(x(elements, index));
        }
        return Optional.empty();
    }
}
