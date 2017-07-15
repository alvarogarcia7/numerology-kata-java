package com.example.kata.test.helper;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

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

    public Option<List<Integer>> at(int index) {
        return rule.apply(Elements.in(this.input), index);
    }
}
