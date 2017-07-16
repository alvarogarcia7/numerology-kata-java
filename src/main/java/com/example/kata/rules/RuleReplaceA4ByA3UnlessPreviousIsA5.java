package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import com.example.kata.numerology.selectors.PreviousSelector;
import io.vavr.control.Option;

import java.util.List;

import static java.util.Arrays.asList;

public class RuleReplaceA4ByA3UnlessPreviousIsA5 implements Rule {
    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        return new PreviousSelector().apply(elements, index)
                .filter(previous -> !previous.equals(5))
                .map(i -> asList(4));
    }
}
