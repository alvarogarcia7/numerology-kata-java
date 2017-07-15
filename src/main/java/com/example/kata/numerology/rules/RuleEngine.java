package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.Numerology;
import io.vavr.control.Option;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static java.util.Arrays.stream;

public class RuleEngine {
    private final Rule[] rules;

    public RuleEngine(Rule[] rules) {
        this.rules = rules;
    }

    /**
     * @return an Elements as this function injects behavior to the Elements class. As such, this function must talk in the terms of the injectee class
     * @param elements
     * @param index
     */
    public Elements applyAllRules(Elements elements, Integer index) {
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
