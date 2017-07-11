package com.example.kata.numerology;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Numerology {
    public List<Integer> replace(List<Integer> input) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            Integer integer = input.get(i);
            if (integer.equals(9)) {
                result.add(10);
                result.add(10);
            } else if(integer.equals(2)) {
                Integer previousNumber = input.get(i - 1);
                IntStream.rangeClosed(1, previousNumber).forEach((x) -> result.add(1));
            } else {
                result.add(integer);
            }
        }
        return result;
    }
}
