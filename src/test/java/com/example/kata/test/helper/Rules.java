package com.example.kata.test.helper;

import com.example.kata.numerology.rules.Rule;
import com.example.kata.numerology.rules.RuleReplace9ForTwo10s;
import com.example.kata.numerology.rules.RuleReplace2ForAnEqualAmountOfNumbersToTheLeft;
import com.example.kata.numerology.rules.RuleReplace6ForAsMany3AsTheValueToTheNthRight;
import com.example.kata.rules.RuleReplaceA3ByA5UnlessNextIsA5;
import com.example.kata.rules.RuleReplaceA4ByA3UnlessPreviousIsA5;
import com.example.kata.test.unit.rule.RuleReplaceA4ByA3UnlessPreviousIsA5Should;
import io.vavr.control.Option;

import java.util.List;

public class Rules {
    public static Rule rule1() {
        return new RuleReplace9ForTwo10s();
    }

    public static RuleReplace2ForAnEqualAmountOfNumbersToTheLeft rule2() {
        return new RuleReplace2ForAnEqualAmountOfNumbersToTheLeft();
    }

    public static RuleReplace6ForAsMany3AsTheValueToTheNthRight rule3() {
        return new RuleReplace6ForAsMany3AsTheValueToTheNthRight();
    }

    /**
     * Tautology = always is true
     * In this case, always applies
     *
     */
    public static Rule tautologyThatProduces(final List<Integer> value) {
        return (elements, index) -> Option.of(value);
    }

    /**
     * Contradiction = something that is always false
     * In this case, it never applies, therefore always return an empty list
     */
    public static Rule contradiction() {
        return (elements, index) -> Option.none();
    }

    public static Rule rule4() {
        return new RuleReplaceA3ByA5UnlessNextIsA5();
    }

    public static Rule rule5() {
        return new RuleReplaceA4ByA3UnlessPreviousIsA5();
    }
}
