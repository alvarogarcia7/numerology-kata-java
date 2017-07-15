package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.Rule;
import io.vavr.control.Option;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Insert3sRule implements Rule {
    private final Integer numberOfTimes;

    public Insert3sRule(Integer numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        return Option.of(IntStream.rangeClosed(1, numberOfTimes).map((x) -> 3).boxed().collect(toList()));
    }
}
