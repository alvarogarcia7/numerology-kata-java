package com.example.kata.numerology;

import com.example.kata.numerology.rules.Rule9ForTwo10s;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NumerologyWithRules {

    private Rule9ForTwo10s rule9ForTwo10s;
    private RuleReplace2ForAnEqualAmountOfNumbersToTheLeft rule2;

    public NumerologyWithRules(Rule9ForTwo10s rule9ForTwo10s, RuleReplace2ForAnEqualAmountOfNumbersToTheLeft rule2) {
        this.rule9ForTwo10s = rule9ForTwo10s;
        this.rule2 = rule2;
    }

    public List<Integer> replace(List<Integer> input) {
        Elements elements = Elements.in(input);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; elements.exists(i); i++) {
            Integer integer = elements.at(i);
            rule2.apply(elements, i).map(result::addAll);
            if (integer.equals(9)) {
                result.addAll(this.rule9ForTwo10s.apply(elements, i).get());
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
