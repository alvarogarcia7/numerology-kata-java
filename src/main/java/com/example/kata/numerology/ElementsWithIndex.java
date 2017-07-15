package com.example.kata.numerology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class ElementsWithIndex {
    private Map<Integer, Integer> values;

    public ElementsWithIndex(Map<Integer, Integer> values) {
        this.values = values;
    }

    public static ElementsWithIndex of(Map<Integer, Integer> values) {
        return new ElementsWithIndex(values);
    }

    public Elements flatMap(BiFunction<ElementsWithIndex, Integer, Elements> mapper) {
        Elements result = new Elements(new ArrayList<>());
        for (Map.Entry<Integer, Integer> integerIntegerEntry : values.entrySet()) {
            result = result.append(mapper.apply(this.noIndex(), integerIntegerEntry.getValue()));
        }
        return result;
    }

    public Elements noIndex() {
        return Elements.in(new ArrayList<>(values.values()));
    }
}
