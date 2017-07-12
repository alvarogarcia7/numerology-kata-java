package com.example.kata.numerology;

import com.example.kata.numerology.rules.Rule9ForTwo10s;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class NumerologyWithRules {

    private Rule9ForTwo10s rule9ForTwo10s;

    public NumerologyWithRules(Rule9ForTwo10s rule9ForTwo10s) {
        this.rule9ForTwo10s = rule9ForTwo10s;
    }

    public List<Integer> replace(List<Integer> input) {
        Elements elements = Elements.in(input);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; elements.exists(i); i++) {
            Integer integer = elements.at(i);
            if (integer.equals(9)) {
                result.addAll(this.rule9ForTwo10s.apply(elements));
            } else if (integer.equals(2) && elements.exists(i - 1)) {
                Integer previousNumber = elements.previousOf(i);
                IntStream.rangeClosed(1, previousNumber).forEach((x) -> result.add(1));
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