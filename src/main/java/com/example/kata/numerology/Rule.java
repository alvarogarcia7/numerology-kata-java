package com.example.kata.numerology;

import java.util.List;
import java.util.Optional;

public interface Rule {
    Optional<List<Integer>> apply(Elements elements, int index);
}
