package com.example.kata.numerology;

import java.util.*;

import static java.util.Collections.singletonList;

public class Numerology {

    private final Rule[] rules;

    public Numerology(Rule... rules) {
        this.rules = rules;
    }

    public List<Integer> replace(List<Integer> input) {
        Elements elements = Elements.in(input);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; elements.exists(i); i++) {
            Integer integer = elements.at(i);
            Optional<List<Integer>> appliedRule = Optional.empty();
            for (Rule rule : rules) {
                appliedRule = rule.apply(elements, i);
                if(appliedRule.isPresent()){
                    break;
                }
            }
            result.addAll(appliedRule.orElse(singletonList(integer)));
        }
        return result;
    }
}
