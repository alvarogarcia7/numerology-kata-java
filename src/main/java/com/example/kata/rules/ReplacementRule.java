package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.ByIndexSelector;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

import static java.util.Arrays.asList;

public class ReplacementRule implements Rule {
    private final int input;
    private final int output;

    public ReplacementRule(int input, int output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        return new ByIndexSelector().apply(elements, index).filter(element -> element.equals(input)).map(matches -> asList(output));
    }
}
