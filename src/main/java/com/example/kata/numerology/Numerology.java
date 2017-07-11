package com.example.kata.numerology;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Numerology {
    public List<Integer> replace(List<Integer> input) {
        Elements elements = Elements.in(input);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; elements.exists(i); i++) {
            Integer integer = elements.at(i);
            if (integer.equals(9)) {
                result.add(10);
                result.add(10);
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

    private static class Elements {
        private final List<Integer> values;

        public Elements(List<Integer> values) {
            this.values = values;
        }

        public static Elements in(List<Integer> values) {
            return new Elements(values);
        }

        public boolean exists(int i) {
            return i>=0 && i < values.size();
        }

        public Integer at(int index) {
            return values.get(index);
        }

        public Integer previousOf(int index) {
            return values.get(index - 1);
        }
    }
}
