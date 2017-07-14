package com.example.kata.numerology;

import io.vavr.control.Option;

import java.util.List;

public interface Rule {
    Option<List<Integer>> apply(Elements elements, int index);
}
