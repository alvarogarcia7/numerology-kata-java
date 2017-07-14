package com.example.kata.test.unit.rule;

import org.junit.Test;

import java.util.Optional;

import static com.example.kata.test.unit.RuleCaseBuilder.apply;
import static com.example.kata.test.unit.Rules.rule1;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Rule9ForTwo10sShould {

    @Test
    public void rule_1_cannot_access_a_nonexisting_index() {
        assertThat(apply(rule1()).to(asList(2)).at(1), is(Optional.empty()));
    }

    @Test
    public void replace_a_single_nine_by_two_tens() {
        assertThat(apply(rule1()).to(asList(9)).at(0), is(Optional.of(asList(10, 10))));
    }

    @Test
    public void replace_no_nines_by_two_tens_each() {
        assertThat(apply(rule1()).to(asList(1)).at(0), is(Optional.empty()));
    }

}
