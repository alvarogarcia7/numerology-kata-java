package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

import static java.util.Collections.singletonList;

public class RuleIdentity implements Rule {
    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        return Option.of(singletonList(elements.at(index)));
    }
}
