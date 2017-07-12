package com.example.kata.numerology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                    .filter(rule -> rule.apply(elements, index).isPresent())
                    .map(rule -> rule.apply(elements, index).get())
                    .findFirst()
                    .map(result::addAll);
        }
        return result;
    }
}
