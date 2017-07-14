package com.example.kata.test.unit.ruleengine;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.Numerology;
import com.example.kata.numerology.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NumerologyShould {

    @Test
    public void apply_only_the_first_rule_that_matches() {

        Rule tautology1 = tautologyRuleThatProduces(asList(1));
        Rule tautology2 = tautologyRuleThatProduces(asList(2));
        Numerology numerology = new Numerology(tautology1, tautology2);

        List<Integer> result = numerology.replace(asList(9));

        assertThat(result, is(asList(1)));
    }

    @Test
    public void not_depend_on_having_a_catchall_rule() {

        Rule contradiction = contradictionRule();
        Numerology numerology = new Numerology(contradiction);

        List<Integer> result = numerology.replace(asList(9));

        assertThat(result, is(Collections.emptyList()));
    }

    private Rule contradictionRule() {
        return (elements, index) -> Optional.empty();
    }

    /**
     * Tautology = always is true
     * in this case, always applies
     *
     */
    private Rule tautologyRuleThatProduces(final List<Integer> value) {
        Rule tautologyRule1 = (elements, index) -> Optional.of(value);
        return tautologyRule1;
    }

}
