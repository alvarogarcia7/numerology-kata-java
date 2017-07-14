package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.Rule;
import io.vavr.control.Option;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class RuleReplace2ForAnEqualAmountOfNumbersToTheLeft implements Rule {

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        if(!elements.exists(index)){
            return Option.none();
        }
        Integer integer = elements.at(index);
        if (integer.equals(2) && elements.exists(index - 1)) {
            Integer previousNumber = elements.previousOf(index);
            return Option.of(IntStream.rangeClosed(1, previousNumber).map((x) -> 1).boxed().collect(toList()));
        }
        return Option.none();
    }
}
