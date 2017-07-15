package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.selectors.AtADistanceSelector;
import com.example.kata.numerology.selectors.AtADistanceSelector.AtADistanceSelectorFactory;
import com.example.kata.numerology.selectors.PreviousSelector;
import com.example.kata.numerology.selectors.Selector;
import io.vavr.control.Option;

import java.util.List;

public class RuleReplace6ForAsMany3AsTheValueToTheNthRight implements Rule {

    private final Rule withinBounds = new WithinBoundsRule();
    private final Rule equalityTo = new EqualityToRule(6);
    private final Rule hasPrevious = new HasPreviousElementRule();
    private Selector previousSelector = new PreviousSelector();
    private AtADistanceSelectorFactory atADistanceSelectorFactory = AtADistanceSelector.factory();


    @Override
    public Option<List<Integer>> apply(Elements elements, int i) {
        return withinBounds.apply(elements, i)
                .flatMap(r -> equalityTo.apply(elements, i)
                        .flatMap(r2 -> hasPrevious.apply(elements, i)
                                .flatMap(r3b -> {
                                    Option<Integer> previous = previousSelector.apply(elements, i);

                                    Integer distance = elements.at(i - 1);
                                    Selector selector = atADistanceSelectorFactory.aNew(distance);
                                    Option<Integer> repeat = selector.apply(elements, i);
                                    return previous
                                            .flatMap(previous1 -> repeat.flatMap(numberOfTimes -> insert3s(numberOfTimes, i, elements)));
                                })));
    }

    private Option<List<Integer>> insert3s(Integer numberOfTimes, int index, Elements elements) {
        return new Insert3sRule(numberOfTimes).apply(elements, index);
    }

}
