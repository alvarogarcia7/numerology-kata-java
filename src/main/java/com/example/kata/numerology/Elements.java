package com.example.kata.numerology;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public Elements flatMapWithIndex(BiFunction<Elements, Integer, Elements> mapper) {
        Elements result = new Elements(new ArrayList<>());
        for (int i = 0; i < values.size(); i++) {
            result = result.append(mapper.apply(this, i));
        }
        return result;
    }

    private Elements append(Elements elements) {
        List<Integer> integers = new ArrayList<>(this.values);
        integers.addAll(elements.values);
        return new Elements(integers);
    }

    public List<Integer> toList() {
        return Collections.unmodifiableList(values);
    }

}
