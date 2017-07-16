package com.example.kata.rules.gaslimited;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

public class LimitedGasRule implements Rule {
    private final Gas gas;
    private final Rule rule;

    public LimitedGasRule(Rule rule, Gas gas) {
        this.gas = gas;
        this.rule = rule;
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        if (gas.hasGas()) {
            Option<List<Integer>> application = this.rule.apply(elements, index);
            if (application.isDefined()) {
                gas.consume();
                return application;
            }
        }
        return Option.none();
    }
}
