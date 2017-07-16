package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

public class GasConsumingRule {
    private final Rule rule;
    private int availableGas;

    public GasConsumingRule(Rule rule, int availableGas) {
        this.rule = rule;
        this.availableGas = availableGas;
    }

    public void refuel() {
        availableGas += 1;
    }

    public void consumeGas() {
        availableGas -= 1;
    }

    public boolean hasGas() {
        return availableGas > 0;
    }

    public Option<List<Integer>> apply(Elements elements, int index) {
        if (hasGas()) {
            return rule.apply(elements, index);
        }
        return Option.none();
    }
}
