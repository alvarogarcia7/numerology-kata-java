package com.example.kata.numerology.rules;

import com.example.kata.numerology.Elements;
import io.vavr.control.Option;

public abstract class Selector {
    public abstract Option<Integer> apply(Elements elements, int index);
}
