package com.example.kata.numerology;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Numerology {
    public List<Integer> replace(List<Integer> input) {
        return input.stream().flatMap(integer -> {
            if (integer.equals(9)) {
                return Stream.of(10, 10);
            } else {
                return Stream.of(integer);
            }
        }).collect(Collectors.toList());
    }
}
