package com.example.kata.numerology;

import java.util.*;
import java.util.function.Supplier;

import static java.util.Arrays.*;

public class Numerology {

    private final Rule[] rules;

    public Numerology(Rule... rules) {
        this.rules = rules;
    }

    public List<Integer> replace(List<Integer> values) {
        return Elements.in(values)
                .flatMapWithIndex(this::applyAllRules)
                .toList();
    }

    private Elements applyAllRules(Elements elements, Integer index) {
        return Elements.in(
                stream(rules)
                .map(rule -> rule.apply(elements, index))
                .filter(Optional::isPresent)
                .findFirst()
                .orElseGet(emptyList()).get());
    }

    private Supplier<Optional<List<Integer>>> emptyList() {
        return () -> Optional.of(Collections.emptyList());
    }
}
