package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.selectors.Selector;
import io.vavr.control.Option;

public class ByIndexSelector extends Selector {
    @Override
    public Option<Integer> apply(Elements elements, int index) {
        if (elements.exists(index)) {
            return Option.of(elements.at(index));
        }
        return Option.none();
    }
}
