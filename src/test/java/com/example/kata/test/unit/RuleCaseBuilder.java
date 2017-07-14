package com.example.kata.test.unit;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.Rule;

import java.util.List;
import java.util.Optional;

public class RuleCaseBuilder {
    private Rule rule;
    private List<Integer> input;

    public static RuleCaseBuilder apply(Rule rule) {
        RuleCaseBuilder ruleCaseBuilder = new RuleCaseBuilder();
        ruleCaseBuilder.rule = rule;
        return ruleCaseBuilder;
    }

    public RuleCaseBuilder to(List<Integer> input) {
        this.input = input;
        return this;
    }

    public Optional<List<Integer>> at(int index) {
        return rule.apply(Elements.in(this.input), index);
    }
}
