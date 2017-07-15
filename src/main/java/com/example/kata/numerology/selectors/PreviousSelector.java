package com.example.kata.numerology.selectors;


import com.example.kata.numerology.Elements;
import com.example.kata.numerology.selectors.Selector;
import io.vavr.control.Option;

public class PreviousSelector extends Selector {

    @Override
    public Option<Integer> apply(Elements elements, int index) {
        if (elements.exists(index - 1)) {
            return Option.of(elements.at(index - 1));
        }
        return Option.none();
    }
}
