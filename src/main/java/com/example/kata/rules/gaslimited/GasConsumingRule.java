package com.example.kata.rules.gaslimited;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

public class GasConsumingRule implements Rule {
    private final Rule rule;
    private Gas gas;

    public GasConsumingRule(Rule rule, Gas gas) {
        this.rule = rule;
        this.gas = gas;
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        if (gas.hasGas()) {
            Option<List<Integer>> application = rule.apply(elements, index);
            if (application.isDefined()) {
                gas.consume();
            }
            return application;
        }
        return Option.none();
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
