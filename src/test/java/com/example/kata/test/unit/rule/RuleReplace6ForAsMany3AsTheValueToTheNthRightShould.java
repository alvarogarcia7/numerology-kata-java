package com.example.kata.test.unit.rule;

import io.vavr.control.Option;
import org.junit.Test;


import static com.example.kata.test.helper.RuleCaseBuilder.apply;
import static com.example.kata.test.helper.Rules.rule3;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RuleReplace6ForAsMany3AsTheValueToTheNthRightShould {
    @Test
    public void not_apply_rule_3_to_numbers_that_not_match_the_specification() {
        assertThat(apply(rule3()).to(asList(2)).at(0), is(Option.none()));
    }

    @Test
    public void rule_3_cannot_access_a_nonexisting_index() {
        assertThat(apply(rule3()).to(asList(2)).at(1), is(Option.none()));
    }

    @Test
    public void replace_rule_3() {
        assertThat(apply(rule3()).to(asList(1, 6, 3)).at(1), is(Option.of(asList(3, 3, 3))));
    }

    @Test
    public void replace_rule_3_cant_because_of_the_left() {
        assertThat(apply(rule3()).to(asList(6)).at(0), is(Option.none()));
    }

    @Test
    public void replace_rule_3_cant_because_of_the_right() {
        assertThat(apply(rule3()).to(asList(4, 6, 3, 4, 5)).at(1), is(Option.none()));
        assertThat(apply(rule3()).to(asList(1, 6)).at(1), is(Option.none()));
    }
}
