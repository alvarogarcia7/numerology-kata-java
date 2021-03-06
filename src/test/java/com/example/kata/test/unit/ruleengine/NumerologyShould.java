package com.example.kata.test.unit.ruleengine;

import com.example.kata.numerology.Numerology;
import com.example.kata.numerology.rules.Rule;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.example.kata.test.helper.Rules.contradiction;
import static com.example.kata.test.helper.Rules.tautologyThatProduces;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NumerologyShould {

    @Test
    public void apply_only_the_first_rule_that_matches() {
        Rule tautology1 = tautologyThatProduces(asList(1));
        Rule tautology2 = tautologyThatProduces(asList(2));
        Numerology numerology = new Numerology(tautology1, tautology2);

        List<Integer> result = numerology.replace(asList(9));

        assertThat(result, is(asList(1)));
    }

    @Test
    public void not_depend_on_having_a_catchall_rule() {
        Numerology numerology = new Numerology(contradiction());

        List<Integer> result = numerology.replace(asList(9));

        assertThat(result, is(Collections.emptyList()));
    }

}
