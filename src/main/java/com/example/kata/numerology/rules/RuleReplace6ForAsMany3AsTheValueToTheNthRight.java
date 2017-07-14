package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.Rule;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class RuleReplace6ForAsMany3AsTheValueToTheNthRight implements Rule {

    @Override
    public Optional<List<Integer>> apply(Elements elements, int i) {
        if (!elements.exists(i)) {
            return Optional.empty();
        }
        Integer integer = elements.at(i);
        if (integer.equals(6) && (elements.exists(i - 1)) && elementExists(elements, i + previous(elements, i))) {
            Integer previous = elements.previousOf(i);
            int numberOfTimes = elements.at(previous + i);
            return Optional.of(IntStream.rangeClosed(1, numberOfTimes).map((x) -> 3).boxed().collect(toList()));
        }
        return Optional.empty();

    }

    private boolean elementExists(Elements elements, int index) {
        return elements.exists(index);
    }

    private Integer previous(Elements elements, int i) {
        return elements.at(i - 1);
    }
}
