package com.example.kata.numerology;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NumerologyWithRules {

    private Rule rule;
    private Rule rule2;

    public NumerologyWithRules(Rule rule, Rule rule2) {
        this.rule = rule;
        this.rule2 = rule2;
    }

    public List<Integer> replace(List<Integer> input) {
        Elements elements = Elements.in(input);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; elements.exists(i); i++) {
            Integer integer = elements.at(i);
            rule2.apply(elements, i).map(result::addAll);
            rule.apply(elements, i).map(result::addAll);
            if (integer.equals(9)) {
            } else if (integer.equals(2) && elements.exists(i - 1)) {
                //can't remove this yet
            } else if (integer.equals(6) && (elements.exists(i - 1)) && elementExists(input, i + previous(input, i))) {
                Integer previous = elements.previousOf(i);
                int numberOfTimes = elements.at(previous + i);
                IntStream.rangeClosed(1, numberOfTimes).forEach((x) -> result.add(3));
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
