package com.example.kata.numerology;

import java.util.List;

class Elements {
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
