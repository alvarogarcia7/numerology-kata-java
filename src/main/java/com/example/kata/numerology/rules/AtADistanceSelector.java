package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import io.vavr.control.Option;

class AtADistanceSelector extends Selector {
    private final Integer distance;

    private AtADistanceSelector(Integer distance) {
        this.distance = distance;
    }

    public static AtADistanceSelectorFactory factory(){
        return new AtADistanceSelectorFactory();
    }

    @Override
    public Option<Integer> apply(Elements elements, int index) {
        if (elements.exists(index + distance)) {
            return Option.of(elements.at(index + distance));
        }
        return Option.none();
    }

    static class AtADistanceSelectorFactory {
        public AtADistanceSelector aNew(Integer distance) {
            return new AtADistanceSelector(distance);
        }
    }
}
