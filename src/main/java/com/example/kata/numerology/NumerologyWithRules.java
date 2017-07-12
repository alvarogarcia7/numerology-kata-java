package com.example.kata.numerology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
            final Integer index = i;
            Arrays.stream(rules).forEachOrdered(rule -> rule.apply(elements, index).map(result::addAll));
            if (integer.equals(9)) {
            } else if (integer.equals(2) && elements.exists(i - 1)) {
                //can't remove this yet
            } else if (integer.equals(6) && (elements.exists(i - 1)) && elementExists(input, i + previous(input, i))) {
            } else {
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
