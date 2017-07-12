package com.example.kata.numerology;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class RuleReplace2ForAnEqualAmountOfNumbersToTheLeft implements Rule {

    @Override
    public Optional<List<Integer>> apply(Elements elements, int index) {
        Integer integer = elements.at(index);
        if (integer.equals(2) && elements.exists(index - 1)) {
            Integer previousNumber = elements.previousOf(index);
            return Optional.of(IntStream.rangeClosed(1, previousNumber).map((x) -> 1).boxed().collect(toList()));
        }
        return Optional.empty();
    }
}
