package com.example.kata.test.helper;

import com.example.kata.numerology.Rule;
import com.example.kata.numerology.rules.Rule9ForTwo10s;
import com.example.kata.numerology.rules.RuleReplace2ForAnEqualAmountOfNumbersToTheLeft;
import com.example.kata.numerology.rules.RuleReplace6ForAsMany3AsTheValueToTheNthRight;

import java.util.List;
import java.util.Optional;

public class Rules {
    public static Rule rule1() {
        return new Rule9ForTwo10s();
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
        return (elements, index) -> Optional.of(value);
    }

    /**
     * Contradiction = something that is always false
     * In this case, it never applies, therefore always return an empty list
     */
    public static Rule contradiction() {
        return (elements, index) -> Optional.empty();
    }
}
