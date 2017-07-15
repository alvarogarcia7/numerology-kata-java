package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import io.vavr.control.Option;

import java.util.List;

public class EqualityToRule implements Rule {
    private final int value;

    public EqualityToRule(int value) {
        this.value = value;
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        if(elements.at(index).equals(value)){
            return Option.of(elements.toList());
        }
        return Option.none();
    }
}
