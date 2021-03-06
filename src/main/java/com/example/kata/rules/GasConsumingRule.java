package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Gas;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

public class GasConsumingRule implements Rule, Gas {
    private final Rule rule;
    private GasTank gasTank;

    public GasConsumingRule(Rule rule, GasTank availableGas) {
        this.rule = rule;
        this.gasTank = availableGas;
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

    @Override
    public void refuel () {
        gasTank = gasTank.refuel();
    }

    @Override
    public void consumeGas () {
        gasTank = gasTank.consume();
    }

    private boolean hasGas() {
        return !gasTank.isEmpty();
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
