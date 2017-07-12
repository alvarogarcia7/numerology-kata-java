package com.example.kata.numerology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Numerology {

    private final Rule[] rules;

    public Numerology(Rule... rules) {
        this.rules = rules;
    }

    public List<Integer> replace(List<Integer> input) {
        Elements elements = Elements.in(input);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; elements.exists(i); i++) {
            final Integer index = i;
            Arrays
                    .stream(rules)
                    .map(rule -> rule.apply(elements, index))
                    .filter(Optional::isPresent)
                    .findFirst()
                    .map(firstRuleThatApplies -> firstRuleThatApplies.map(result::addAll));
        }
        return result;
    }
}
