package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.Rule;
import io.vavr.control.Option;

import java.util.List;

public class WithinBoundsRule implements Rule {
    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        if(!elements.exists(index)){
            return Option.none();
        }
        return Option.of(elements.toList());
    }
}
