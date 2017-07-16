package com.example.kata.test.unit.rule;

import com.example.kata.numerology.rules.Rule;
import com.example.kata.test.helper.Rules;
import io.vavr.control.Option;
import org.junit.Test;

import static com.example.kata.test.helper.RuleCaseBuilder.apply;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RuleReplaceA3ByA5UnlessNextIsA5Should {

    @Test
    public void happy_path() {
        assertThat(apply(rule()).to(asList(3, 4)).at(0), is(Option.of(asList(5))));
    }

    @Test
    public void no_replace_as_next_is_a_5() {
        assertThat(apply(rule()).to(asList(3, 5)).at(0), is(Option.none()));
    }


    /**
     * If the element is at the end of the sequence, it is not immediately succeeded by a 5
     */
    @Test
    public void replace_as_the_end_of_the_sequence() {
        assertThat(apply(rule()).to(asList(3)).at(0), is(Option.of(asList(5))));
    }

    private Rule rule() {
        return Rules.rule4();
    }
}
