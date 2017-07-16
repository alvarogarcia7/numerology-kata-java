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
    public void try_to_apply_the_rule_the_first_time() {
        assertThat(apply(rule()).to(asList(4, 4)).at(0), is(Option.of(asList(1))));
    }
    @Test
    public void only_replace_a_second_4_after_having_replaced_a_3() {
        assertThat(apply(rule()).to(asList(4, 4)).at(1), is(Option.none()));
    }

    private Rule rule() {
        return new Rule4And5(Rules.tautologyThatProduces(asList(1)), Rules.tautologyThatProduces(asList(2)));
    }


}
