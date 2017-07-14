package com.example.kata.test.unit.ruleengine;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.Numerology;
import com.example.kata.numerology.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NumerologyShould {

    @Test
    public void apply_only_the_first_rule_that_matches() {

        Rule tautology1 = tautologyRule(asList(1));
        Rule tautology2 = tautologyRule(asList(2));
        Numerology numerology = new Numerology(tautology1, tautology2);

        List<Integer> result = numerology.replace(asList(9));

        assertThat(result, is(asList(1)));
    }

    /**
     * Tautology = always is true
     * in this case, always applies
     *
     */
    private Rule tautologyRule(final List<Integer> value) {
        Rule tautologyRule1 = new Rule() {
            @Override
            public Optional<List<Integer>> apply(Elements elements, int index) {
                return Optional.of(value);
            }
        };
        return tautologyRule1;
    }

}
