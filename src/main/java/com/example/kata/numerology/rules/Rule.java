package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import io.vavr.control.Option;

import java.util.List;

public interface Rule {
    Option<List<Integer>> apply(Elements elements, int index);
}
