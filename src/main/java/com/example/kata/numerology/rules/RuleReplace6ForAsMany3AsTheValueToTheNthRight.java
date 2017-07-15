package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.Rule;
import io.vavr.control.Option;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class RuleReplace6ForAsMany3AsTheValueToTheNthRight implements Rule {

    private final Rule withinBounds = new WithinBoundsRule();
    private final Rule equalityTo = new EqualityToRule(6);
    private final Rule hasPrevious = new HasPreviousElementRule();

    @Override
    public Option<List<Integer>> apply(Elements elements, int i) {
        return withinBounds
                .apply(elements, i)
                .flatMap(r -> equalityTo
                        .apply(elements, i)
                        .flatMap(r2 -> hasPrevious.apply(elements, i).flatMap(r3 -> {
                            if (elementExists(elements, i + previous(elements, i))) {
                                Integer previous = elements.previousOf(i);
                                int numberOfTimes = elements.at(previous + i);
                                return Option.of(IntStream.rangeClosed(1, numberOfTimes).map((x) -> 3).boxed().collect(toList()));
                            }
                            return Option.none();
                        })));

    }

    private boolean elementExists(Elements elements, int index) {
        return elements.exists(index);
    }

    private Integer previous(Elements elements, int i) {
        return elements.at(i - 1);
    }
}
