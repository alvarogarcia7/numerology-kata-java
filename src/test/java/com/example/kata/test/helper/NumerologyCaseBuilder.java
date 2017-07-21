package com.example.kata.test.helper;

import com.example.kata.numerology.Numerology;
import com.example.kata.numerology.rules.Rule;

import java.util.List;

public class NumerologyCaseBuilder {
    private List<Integer> input;
    private Numerology sut;

    public static NumerologyCaseBuilder applyingAllRules(Rule... rules) {
        NumerologyCaseBuilder ruleCaseBuilder = new NumerologyCaseBuilder();
        ruleCaseBuilder.sut = new Numerology(rules);
        return ruleCaseBuilder;
    }

    public List<Integer> to(List<Integer> input) {
        return sut.replace(input);
    }
}
