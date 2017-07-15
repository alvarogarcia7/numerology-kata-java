package com.example.kata.numerology.selectors;

import com.example.kata.numerology.Elements;
import io.vavr.control.Option;

public class AtADistanceSelector extends Selector {
    private final Integer distance;

    private AtADistanceSelector(Integer distance) {
        this.distance = distance;
    }

    public static AtADistanceSelectorFactory factory(){
        return new AtADistanceSelectorFactory();
    }

    @Override
    public Option<Integer> apply(Elements elements, int index) {
        int desiredIndex = index + distance;
        if (elements.exists(desiredIndex)) {
            return Option.of(elements.at(desiredIndex));
        }
        return Option.none();
    }

    public static class AtADistanceSelectorFactory {
        public AtADistanceSelector aNew(Integer distance) {
            return new AtADistanceSelector(distance);
        }
    }
}
