package com.example.kata.test.unit.rule;

import com.example.kata.numerology.rules.Rule;
import com.example.kata.test.helper.Rules;
import io.vavr.control.Option;
import org.junit.Test;

import static com.example.kata.test.helper.RuleCaseBuilder.apply;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReplacementRuleShould {


    @Test
    public void replace_the_matching_number() {
        assertThat(apply(rule()).to(asList(0)).at(0), is(Option.of(asList(1))));
    }

    @Test
    public void not_replace_the_other_numbers() {
        assertThat(apply(rule()).to(asList(1)).at(0), is(Option.none()));
    }

    private Rule rule() {
        return Rules.replacement(0, 1);
    }

}
