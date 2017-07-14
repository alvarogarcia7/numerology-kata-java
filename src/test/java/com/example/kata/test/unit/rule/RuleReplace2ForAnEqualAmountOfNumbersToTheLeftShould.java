package com.example.kata.test.unit.rule;

import org.junit.Test;

import java.util.Optional;

import static com.example.kata.test.helper.RuleCaseBuilder.apply;
import static com.example.kata.test.helper.Rules.rule2;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RuleReplace2ForAnEqualAmountOfNumbersToTheLeftShould {

    @Test
    public void replace_the_number_previous_to_a_2_for_as_many_ones() {
        assertThat(apply(rule2()).to(asList(8, 2)).at(1), is(Optional.of(asList(1, 1, 1, 1, 1, 1, 1, 1))));
    }

    @Test
    public void rule_2_cannot_access_a_nonexisting_index() {
        assertThat(apply(rule2()).to(asList(2)).at(1), is(Optional.empty()));
    }

    @Test
    public void replace_the_number_previous_to_a_2_for_as_many_ones_but_its_not_possible() {
        assertThat(apply(rule2()).to(asList(2)).at(0), is(Optional.empty()));
    }

}
