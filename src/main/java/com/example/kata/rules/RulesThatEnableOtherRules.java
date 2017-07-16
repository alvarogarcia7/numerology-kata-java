package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;
import java.util.function.Consumer;

public class RulesThatEnableOtherRules implements Rule {
    private final GasConsumingRule gasConsumingRule1;
    private final GasConsumingRule gasConsumingRule2;
    private final RefuelingScheme refuelingScheme;

    public RulesThatEnableOtherRules(Rule rule1, Rule rule2) {
        gasConsumingRule1 = new GasConsumingRule(rule1, 1);
        gasConsumingRule2 = new GasConsumingRule(rule2, 1);
        Consumer<GasConsumingRule> gasConsumingRuleGasConsumingRuleFunction = gasConsumingRule -> {
            if(gasConsumingRule2.equals(gasConsumingRule)){
                gasConsumingRule2.consumeGas();
                gasConsumingRule1.refuel();
            } else if(gasConsumingRule1.equals(gasConsumingRule)) {
                gasConsumingRule2.refuel();
                gasConsumingRule1.consumeGas();
            }
        };
        refuelingScheme = new RefuelingScheme(gasConsumingRuleGasConsumingRuleFunction);
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        //calculate state of rules until now
        for (int i = 0; i < index; i++) {
            if (gasConsumingRule1.apply(elements, i).isDefined()) {
                refuelingScheme.apply(gasConsumingRule1);
//                gasConsumingRule1.consumeGas();
//                gasConsumingRule2.refuel();
                continue;
            }
            if (gasConsumingRule2.apply(elements, i).isDefined()) {
                refuelingScheme.apply(gasConsumingRule2);
//                gasConsumingRule1.refuel();
//                gasConsumingRule2.consumeGas();
                continue;
            }
        }

        return gasConsumingRule1.apply(elements, index)
                .orElse(gasConsumingRule2.apply(elements, index));
    }

    private class RefuelingScheme {
        private final Consumer<GasConsumingRule> scheme;

        public RefuelingScheme(Consumer<GasConsumingRule> scheme) {
            this.scheme = scheme;
        }

        public void apply(GasConsumingRule gasConsumingRule) {
            this.scheme.accept(gasConsumingRule);
        }
    }
}
