package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

public class RulesThatEnableOtherRules implements Rule {
    private final GasConsumingRule gasConsumingRule1;
    private final GasConsumingRule gasConsumingRule2;

    public RulesThatEnableOtherRules(Rule rule1, Rule rule2) {
        gasConsumingRule1 = new GasConsumingRule(rule1, 1);
        gasConsumingRule2 = new GasConsumingRule(rule2, 1);
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        //calculate state of rules until now
        for (int i = 0; i < index; i++) {
            if (gasConsumingRule1.apply(elements, i).isDefined()) {
                gasConsumingRule1.consumeGas();
                gasConsumingRule2.refuel();
                continue;
            }
            if (gasConsumingRule2.apply(elements, i).isDefined()) {
                gasConsumingRule1.refuel();
                gasConsumingRule2.consumeGas();
                continue;
            }
        }
        
        if (gasConsumingRule1.apply(elements, index).isDefined()) {
            return gasConsumingRule1.apply(elements, index).get();
        }
        if (gasConsumingRule2.apply(elements, index).isDefined()) {
            return gasConsumingRule2.apply(elements, index).get();
        }

        return Option.none();
    }

}
