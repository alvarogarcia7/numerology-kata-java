package com.example.kata.numerology;

import com.example.kata.numerology.rules.Rule;
import com.example.kata.numerology.ruleengine.RuleEngine;

import java.util.List;

public class Numerology {

    private final RuleEngine ruleEngine;

    public Numerology(Rule... rules) {
        this.ruleEngine = new RuleEngine(rules);
    }

    public List<Integer> replace(List<Integer> values) {
        return Elements.in(values)
                .flatMapWithIndex(ruleEngine::applyAllRules)
                .toList();
    }

}
