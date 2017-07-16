package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.selectors.AtADistanceSelector;
import com.example.kata.numerology.selectors.AtADistanceSelector.AtADistanceSelectorFactory;
import com.example.kata.numerology.selectors.PreviousSelector;
import com.example.kata.numerology.selectors.Selector;
import io.vavr.control.Option;

import java.util.List;

public class RuleReplace6ForAsMany3AsTheValueToTheNthRight implements Rule {

    private final Rule equalityTo = new EqualityToRule(6);
    private ByIndexSelector byIndexSelector = new ByIndexSelector();
    private Selector previousSelector = new PreviousSelector();
    private AtADistanceSelectorFactory atADistanceSelectorFactory = AtADistanceSelector.factory();


    @Override
    public Option<List<Integer>> apply(Elements elements, int i) {
        return byIndexSelector.apply(elements, i)
                .flatMap(r -> equalityTo.apply(elements, i)
                        .flatMap(r2 -> previousSelector.apply(elements, i)
                                .flatMap(previous -> {
                                    Option<Integer> times = atADistanceSelectorFactory.aNew(previous).apply(elements, i);
                                    return times.flatMap(numberOfTimes -> insert3s(numberOfTimes, i, elements));
                                })));
    }

    private Option<List<Integer>> insert3s(Integer numberOfTimes, int index, Elements elements) {
        return new Insert3sRule(numberOfTimes).apply(elements, index);
    }

}
