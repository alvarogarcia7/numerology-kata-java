package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;
import java.util.function.Consumer;

public class RulesThatEnableOtherRules implements Rule {
    private GasConsumingRule gasConsumingRule1;
    private final Rule rule1;
    private GasConsumingRule gasConsumingRule2;
    private final Rule rule2;
    private final GasTank availableGas2;
    private final GasTank availableGas1;
    private RefuelingScheme refuelingScheme;

    public RulesThatEnableOtherRules (Rule rule1, Rule rule2, final GasTank availableGas1, final GasTank
            availableGas2) {
        this.rule1 = rule1;
        this.rule2 = rule2;
        this.availableGas1 = availableGas1;
        this.availableGas2 = availableGas2;
        initialize(rule1, rule2);
    }

    private void initialize (final Rule rule1, final Rule rule2) {
        gasConsumingRule1 = new GasConsumingRule(rule1, availableGas1.clone_());
        gasConsumingRule2 = new GasConsumingRule(rule2, availableGas2.clone_());
        this.refuelingScheme = alternatingRefuelingScheme(gasConsumingRule1, gasConsumingRule2);
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        //calculate state of rules until now

        initialize(rule1, rule2);
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
