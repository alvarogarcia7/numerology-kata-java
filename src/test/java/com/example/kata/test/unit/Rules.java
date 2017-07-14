package com.example.kata.test.unit;

import com.example.kata.numerology.Rule;
import com.example.kata.numerology.rules.Rule9ForTwo10s;
import com.example.kata.numerology.rules.RuleReplace2ForAnEqualAmountOfNumbersToTheLeft;
import com.example.kata.numerology.rules.RuleReplace6ForAsMany3AsTheValueToTheNthRight;

public class Rules {
    public static Rule rule1() {
        return new Rule9ForTwo10s();
    }

    static RuleReplace2ForAnEqualAmountOfNumbersToTheLeft rule2() {
        return new RuleReplace2ForAnEqualAmountOfNumbersToTheLeft();
    }

    static RuleReplace6ForAsMany3AsTheValueToTheNthRight rule3() {
        return new RuleReplace6ForAsMany3AsTheValueToTheNthRight();
    }
}
