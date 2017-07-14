package com.example.kata.numerology;

import java.util.*;
import java.util.function.Supplier;

public class Numerology {

    private final Rule[] rules;

    public Numerology(Rule... rules) {
        this.rules = rules;
    }

    public List<Integer> replace(List<Integer> values) {
        Elements input = Elements.in(values);
        return input
                .flatMapWithIndex(this::applyAllRules)
                .toList();
    }

    private Elements applyAllRules(Elements elements, Integer index) {
        return Elements.in(Arrays
                .stream(rules)
                .map(rule -> rule.apply(elements, index))
                .filter(Optional::isPresent)
                .findFirst()
                .orElseGet(emptyList()).get());
    }

    private Supplier<Optional<List<Integer>>> emptyList() {
        return () -> Optional.of(Collections.emptyList());
    }
}
