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
    private Selector selector = new PreviousSelector();


    @Override
    public Option<List<Integer>> apply(Elements elements, int i) {
        return withinBounds.apply(elements, i)
                .flatMap(r -> equalityTo.apply(elements, i)
                        .flatMap(r2 -> hasPrevious.apply(elements, i)
                                .flatMap(r3b -> {
                                    Option<Integer> previous = selector.apply(elements, i);

                                    Integer distance = elements.at(i - 1);
                                    Selector selector = new AtADistanceSelector(distance);
                                    Selector atADistanceSelector = selector;
                                    Option<Integer> repeat = atADistanceSelector.apply(elements, i);
                                    return previous.flatMap(previous1 -> repeat.flatMap(numberOfTimes -> Option.of(IntStream.rangeClosed(1, numberOfTimes).map((x) -> 3).boxed().collect(toList()))));
                                })));
    }

    private static class AtADistanceSelector extends Selector {
        private final Integer distance;

        public AtADistanceSelector(Integer distance) {
            this.distance = distance;
        }

        @Override
        public Option<Integer> apply(Elements elements, int index) {
            if (elements.exists(index + distance)) {
                return Option.of(elements.at(index + distance));
            }
            return Option.none();
        }
    }
}
