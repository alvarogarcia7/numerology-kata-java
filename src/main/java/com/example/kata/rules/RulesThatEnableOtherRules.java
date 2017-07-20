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
        this.refuelingScheme = alternatingRefuelingScheme(gasConsumingRule1, gasConsumingRule2);
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        //calculate state of rules until now
        for (int i = 0; i < index; i++) {
            final int finalI = i;
            gasConsumingRule1.apply(elements, i).map(x -> {
                refuelingScheme.apply(gasConsumingRule1);
                return true;
            }).orElse(() -> {
                gasConsumingRule2.apply(elements, finalI).forEach(x -> refuelingScheme.apply(gasConsumingRule2));
                return Option.of(true);
            });
        }

        return gasConsumingRule1.apply(elements, index)
                .orElse(gasConsumingRule2.apply(elements, index));
    }

    RefuelingScheme alternatingRefuelingScheme(GasConsumingRule rule1, GasConsumingRule rule2) {
        // This is a limitation of the java language, a lack of pattern matching.
        // Then, use `equals` for finding out which Rule is it
        Consumer<GasConsumingRule> gasConsumingRuleGasConsumingRuleFunction = gasConsumingRule -> {
            if(rule2.equals(gasConsumingRule)){
                rule1.refuel();
            } else if(rule1.equals(gasConsumingRule)) {
                rule2.refuel();
            }
        };
        return new RefuelingScheme(gasConsumingRuleGasConsumingRuleFunction);
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
