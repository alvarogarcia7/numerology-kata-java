package com.example.kata.test.unit.rule;

import io.vavr.control.Option;
import org.junit.Test;

import static com.example.kata.test.helper.RuleCaseBuilder.apply;
import static com.example.kata.test.helper.Rules.rule1;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RuleReplace9ForTwo10SShould {

    @Test
    public void rule_1_cannot_access_a_nonexisting_index() {
        assertThat(apply(rule1()).to(asList(2)).at(1), is(Option.none()));
    }

    @Test
    public void replace_a_single_nine_by_two_tens() {
        assertThat(apply(rule1()).to(asList(9)).at(0), is(Option.of(asList(10, 10))));
    }

    @Test
    public void replace_no_nines_by_two_tens_each() {
        assertThat(apply(rule1()).to(asList(1)).at(0), is(Option.none()));
    }

}
