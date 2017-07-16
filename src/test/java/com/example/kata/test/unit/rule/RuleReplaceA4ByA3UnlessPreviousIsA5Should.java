package com.example.kata.test.unit.rule;

import com.example.kata.numerology.rules.Rule;
import com.example.kata.test.helper.Rules;
import io.vavr.control.Option;
import org.junit.Test;

import static com.example.kata.test.helper.RuleCaseBuilder.apply;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RuleReplaceA4ByA3UnlessPreviousIsA5Should {

    @Test
    public void happy_path() {
        assertThat(apply(rule()).to(asList(4, 3)).at(1), is(Option.of(asList(4))));
    }

    @Test
    public void no_replace_as_previous_is_a_5() {
        assertThat(apply(rule()).to(asList(5, 3)).at(1), is(Option.none()));
    }

    private Rule rule() {
        return Rules.rule5();
    }
}
