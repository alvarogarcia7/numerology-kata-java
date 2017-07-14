package com.example.kata.test.unit.rule;

import io.vavr.control.Option;
import org.junit.Test;

import static com.example.kata.test.helper.RuleCaseBuilder.apply;
import static com.example.kata.test.helper.Rules.rule2;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RuleReplace2ForAnEqualAmountOfNumbersToTheLeftShould {

    @Test
    public void replace_the_number_previous_to_a_2_for_as_many_ones() {
        assertThat(apply(rule2()).to(asList(8, 2)).at(1), is(Option.of(asList(1, 1, 1, 1, 1, 1, 1, 1))));
    }

    @Test
    public void rule_2_cannot_access_a_nonexisting_index() {
        assertThat(apply(rule2()).to(asList(2)).at(1), is(Option.none()));
    }

    @Test
    public void replace_the_number_previous_to_a_2_for_as_many_ones_but_its_not_possible() {
        assertThat(apply(rule2()).to(asList(2)).at(0), is(Option.none()));
    }

}
