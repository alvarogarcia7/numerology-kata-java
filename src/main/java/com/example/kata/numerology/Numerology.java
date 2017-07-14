package com.example.kata.numerology;

import io.vavr.control.Option;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static java.util.Arrays.stream;

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

    /**
     * @return an Elements as this function injects behavior to the Elements class. As such, this function must talk in the terms of the injectee class
     */
    private Elements applyAllRules(Elements elements, Integer index) {
        return Elements.in(
                stream(rules)
                .map(rule -> rule.apply(elements, index))
                .filter(Option::isDefined)
                .findFirst()
                .orElseGet(emptyList()).get());
    }

    private Supplier<Option<List<Integer>>> emptyList() {
        return () -> Option.of(Collections.emptyList());
    }
}
