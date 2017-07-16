package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import com.example.kata.rules.gaslimited.Gas;
import com.example.kata.rules.gaslimited.GasConsumingRule;
import io.vavr.Tuple2;
import io.vavr.control.Option;

import java.util.List;
import java.util.function.Consumer;

public class RulesThatEnableOtherRules implements Rule {
    private final GasConsumingRule gasConsumingRule1;
    private final GasConsumingRule gasConsumingRule2;
    private final RefuelingScheme refuelingScheme;

    public RulesThatEnableOtherRules(Rule rule1, Rule rule2) {
        Gas availableGas1 = new Gas(1);
        Gas availableGas2 = new Gas(1);
        gasConsumingRule1 = new GasConsumingRule(rule1, availableGas1);
        gasConsumingRule2 = new GasConsumingRule(rule2, availableGas2);
        Tuple2<Rule, Gas> refuelingScheme1 = new Tuple2<>(gasConsumingRule1, availableGas1);
        Tuple2<Rule, Gas> refuelingScheme2 = new Tuple2<>(gasConsumingRule2, availableGas2);
        this.refuelingScheme = alternatingRefuelingScheme(refuelingScheme1, refuelingScheme2);
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        //calculate state of rules until now
        for (int i = 0; i < index; i++) {
            if (gasConsumingRule1.apply(elements, i).isDefined()) {
                refuelingScheme.apply(gasConsumingRule1);
                continue;
            }
            if (gasConsumingRule2.apply(elements, i).isDefined()) {
                refuelingScheme.apply(gasConsumingRule2);
                continue;
            }
        }

        return gasConsumingRule1.apply(elements, index)
                .orElse(gasConsumingRule2.apply(elements, index));
    }

    RefuelingScheme alternatingRefuelingScheme(Tuple2<Rule, Gas> rule1, Tuple2<Rule, Gas> rule2) {
        // This is a limitation of the java language, a lack of pattern matching.
        // Then, use `equals` for finding out which Rule is it
        Consumer<GasConsumingRule> gasConsumingRuleGasConsumingRuleFunction = gasConsuming -> {
            if(rule2._1.equals(gasConsuming)){
                rule1._2.refuel();
            } else if(rule1._1.equals(gasConsuming)) {
                rule2._2.refuel();
            }
        };
        return new RefuelingScheme(gasConsumingRuleGasConsumingRuleFunction);
    }

    private class RefuelingScheme {
        private final Consumer<GasConsumingRule> scheme;

        public RefuelingScheme(Consumer<GasConsumingRule> scheme) {
            this.scheme = scheme;
        }

        public void apply(GasConsumingRule gasConsuming) {
            this.scheme.accept(gasConsuming);
        }
    }
}
