package com.example.kata.test.unit.rule;

import com.example.kata.numerology.rules.Rule;
import com.example.kata.rules.LimitedGasRule;
import com.example.kata.rules.RulesThatEnableOtherRules;
import org.junit.Test;

import static com.example.kata.test.helper.RuleCaseBuilder.apply;
import static com.example.kata.test.helper.Rules.replacement;
import static io.vavr.control.Option.none;
import static io.vavr.control.Option.of;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RuleComposeRulesThatEnableOtherRulesShould {

    @Test
    public void apply_the_rule_when_there_is_gas_left() {
        assertThat(apply(rule()).to(asList(0)).at(0), is(of(asList(1))));
    }

    @Test
    public void apply_the_other_rule_when_that_rule_has_gas_left() {
        assertThat(apply(rule()).to(asList(0, 1)).at(1), is(of(asList(2))));
    }

    @Test
    public void do_not_apply_rules_without_gas() {
        assertThat(apply(rule()).to(asList(0, 0)).at(1), is(none()));
    }

    @Test
    public void do_not_apply_rules_without_gas_another_case() {
        assertThat(apply(rule()).to(asList(0, 0, 0)).at(2), is(none()));
    }

    @Test
    public void do_apply_the_rule_as_it_doesnot_have_a_limit() {
        assertThat(apply(rule()).to(asList(0, 1, 0)).at(2), is(of(asList(1))));
    }

    @Test
    public void do_not_apply_the_rule_when_it_has_reached_its_maximum_consumed_gas_amount() {
        assertThat(apply(limitedGasrule()).to(asList(0, 1, 0)).at(2), is(none()));
    }

    private Rule rule() {
        return new RulesThatEnableOtherRules(replacement(0, 1), replacement(1, 2));
    }

    private Rule limitedGasrule() {
        return new RulesThatEnableOtherRules(new LimitedGasRule(replacement(0, 1), 1,1), new LimitedGasRule(replacement(1, 2), 1, 1));
    }


}
