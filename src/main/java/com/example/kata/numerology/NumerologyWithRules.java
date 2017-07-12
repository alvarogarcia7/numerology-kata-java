package com.example.kata.numerology;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NumerologyWithRules {

    private final Rule[] rules;

    public NumerologyWithRules(Rule... rules) {
        this.rules = rules;
    }

    public List<Integer> replace(List<Integer> input) {
        Elements elements = Elements.in(input);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; elements.exists(i); i++) {
            Integer integer = elements.at(i);
            Optional<List<Integer>> partialResult = Optional.empty();
            for (Rule rule : rules) {
                partialResult = rule.apply(elements, i);
                if(partialResult.isPresent()){
                    result.addAll(partialResult.get());
                    break;
                }
            }
            if(!partialResult.isPresent()){
                result.add(integer);
            }
        }
        return result;
    }

    private boolean elementExists(List<Integer> input, int index) {
        return index < input.size();
    }

    private Integer previous(List<Integer> input, int i) {
        return input.get(i - 1);
    }

}
