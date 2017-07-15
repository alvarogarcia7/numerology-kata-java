package com.example.kata.numerology;

import java.util.*;
import java.util.function.BiFunction;

public class Elements {
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

    public ElementsWithIndex withIndex() {
        Map<Integer, Integer> values = new HashMap<>();
        for (int i = 0; i < values.size(); i++) {
            values.put(i, values.get(i));
        }
        return ElementsWithIndex.of(values);
    }

    protected Elements append(Elements elements) {
        List<Integer> integers = new ArrayList<>(this.values);
        integers.addAll(elements.values);
        return new Elements(integers);
    }

    public List<Integer> toList() {
        return Collections.unmodifiableList(values);
    }

}
