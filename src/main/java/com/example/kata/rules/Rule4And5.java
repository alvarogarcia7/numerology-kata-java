package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

public class Rule4And5 implements Rule {
    private final GasConsumingRule gasConsumingRule1;
    private final GasConsumingRule gasConsumingRule2;

    public Rule4And5(Rule rule1, Rule rule2) {
        gasConsumingRule1 = new GasConsumingRule(rule1, 1);
        gasConsumingRule2 = new GasConsumingRule(rule2, 1);
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        //calculate state of rules until now
        for (int i = 0; i < index; i++) {
            if (isRule1Applicable() && applyRule1To(elements, i).isDefined()) {
                gasConsumingRule1.consumeGas();
                gasConsumingRule2.refuel();
                continue;
            }
            if (isRule2Applicable() && applyRule2To(elements, i).isDefined()) {
                gasConsumingRule1.refuel();
                gasConsumingRule2.consumeGas();
                continue;
            }
        }

        if (isRule1Applicable() && applyRule1To(elements, index).isDefined()) {
            return applyRule1To(elements, index);
        }
        if (isRule2Applicable() && applyRule2To(elements, index).isDefined()) {
            return applyRule2To(elements, index);
        }

        return Option.none();
    }

    private Option<List<Integer>> applyRule2To(Elements elements, int index) {
        return gasConsumingRule2.apply(elements, index).get();
    }

    private Option<List<Integer>> applyRule1To(Elements elements, int index) {
        return gasConsumingRule1.apply(elements, index).get();
    }

    boolean isRule2Applicable() {
        return gasConsumingRule2.hasGas();
    }

    boolean isRule1Applicable() {
        return gasConsumingRule1.hasGas();
    }
}
