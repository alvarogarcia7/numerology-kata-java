package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.Rule;
import io.vavr.control.Option;

import java.util.List;

public class HasPreviousElementRule implements Rule {
    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        return new WithinBoundsRule().apply(elements, index).flatMap(r -> {
            if (elements.exists(index - 1)) {
                return Option.of(elements.toList());
            }
            return Option.none();
        });
    }

    public static Rule atADistanceOf(Integer distance) {
        return (elements, index) -> new WithinBoundsRule().apply(elements, index).flatMap(r-> {
            if (elements.exists(index - distance)) {
                return Option.of(elements.toList());
            }
            return Option.none();
        });
    }
}
