package com.example.kata.test.unit.rule;

import com.example.kata.numerology.rules.Rule;
import com.example.kata.rules.Rule4And5;
import com.example.kata.test.helper.Rules;
import io.vavr.control.Option;
import org.junit.Test;

import static com.example.kata.test.helper.RuleCaseBuilder.apply;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RuleComposeRule4And5Should {

    @Test
    public void apply_the_rule_when_there_is_gas_left() {
        assertThat(apply(rule()).to(asList(0)).at(0), is(Option.of(asList(1))));
    }

    @Test
    public void apply_the_other_rule_when_that_rule_has_gas_left() {
        assertThat(apply(rule()).to(asList(0, 1)).at(1), is(Option.of(asList(2))));
    }

    @Test
    public void do_not_apply_rules_without_gas() {
        assertThat(apply(rule()).to(asList(0, 0)).at(1), is(Option.none()));
    }

    private Rule rule() {
        return new Rule4And5(Rules.replacement(0, 1), Rules.replacement(1, 2));
    }


}
