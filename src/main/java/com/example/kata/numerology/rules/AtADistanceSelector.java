package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import io.vavr.control.Option;

class AtADistanceSelector extends Selector {
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
