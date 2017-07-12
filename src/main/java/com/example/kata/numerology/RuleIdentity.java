package com.example.kata.numerology;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

public class RuleIdentity implements Rule {
    @Override
    public Optional<List<Integer>> apply(Elements elements, int index) {
        return Optional.of(singletonList(elements.at(index)));
    }
}
