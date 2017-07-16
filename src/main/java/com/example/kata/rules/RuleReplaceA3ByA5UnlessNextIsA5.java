package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

import static io.vavr.control.Option.none;
import static io.vavr.control.Option.of;
import static java.util.Arrays.asList;

public class RuleReplaceA3ByA5UnlessNextIsA5 implements Rule {
    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        if (elements.exists(index + 1) && elements.at(index + 1).equals(5)) {
            return none();
        }
        return of(asList(5));
    }
}
