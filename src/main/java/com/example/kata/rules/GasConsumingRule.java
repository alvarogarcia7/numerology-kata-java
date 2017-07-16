package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

public class GasConsumingRule implements Rule {
    private final Rule rule;
    private int availableGas;

    public GasConsumingRule(Rule rule, int availableGas) {
        this.rule = rule;
        this.availableGas = availableGas;
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        if (hasGas()) {
            Option<List<Integer>> application = rule.apply(elements, index);
            if (application.isDefined()) {
                consumeGas();
            }
            return application;
        }
        return Option.none();
    }

    void refuel() {
        availableGas += 1;
    }

    private void consumeGas() {
        availableGas -= 1;
    }

    private boolean hasGas() {
        return availableGas > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GasConsumingRule that = (GasConsumingRule) o;

        return rule != null ? rule.equals(that.rule) : that.rule == null;
    }

    @Override
    public int hashCode() {
        return rule != null ? rule.hashCode() : 0;
    }
}
