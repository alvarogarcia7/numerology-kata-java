package com.example.kata.test.unit.rule;

import com.example.kata.numerology.rules.Rule;
import com.example.kata.numerology.rules.RuleIdentity;
import com.example.kata.rules.LimitedGasRule;
import com.example.kata.rules.RuleReplaceA3ByA5UnlessNextIsA5;
import com.example.kata.rules.RuleReplaceA4ByA3UnlessPreviousIsA5;
import com.example.kata.rules.RulesThatEnableOtherRules;
import io.vavr.control.Option;
import org.junit.Test;

import java.util.List;

import static com.example.kata.test.helper.NumerologyCaseBuilder.applyingAllRules;
import static com.example.kata.test.helper.RuleCaseBuilder.apply;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RuleStep3_1Should {

    //When replacing the 3's and 4's, you may not replace
    //more than one 3 or 4 in a go without having replaced one instance of the other
    //in between


    //3,X,4,X,3,X,4,X,3,X,4 (valid where X <> 5)
    //5,X,3,X,5,X,3,X,5,X,3

    @Test
    public void apply_the_rule_to_the_first_pair () {
        assertThat(apply(rule()).to(flattened(inputPair())).at(0), is(Option.of(asList(5))));
    }

    @Test
    public void apply_the_second_rule_to_the_first_pair () {
        assertThat(apply(rule()).to(flattened(inputPair())).at(2), is(Option.of(asList(3))));
    }

    @Test
    public void apply_all_rules_to_the_first_pair () {
        assertThat(applyingAllRules(rules()).to(flattened(inputPair())), is(asList(5, 0, 3)));
    }

    private List<Integer> flattened (final List<Integer>... inputs) {
        return inputs[0];
    }


    private List<Integer> inputPair () {
        // This represents a pair of input, although it contains three elements.
        // The element in the middle is a a separator so both rules can be applied,
        // as applying the first one might contradict the application of the second one
        return asList(3, 0, 4);
    }

    private Rule rule() {
        return new RulesThatEnableOtherRules(
                new LimitedGasRule(
                        new RuleReplaceA3ByA5UnlessNextIsA5(), 1, 1),
                new LimitedGasRule(
                        new RuleReplaceA4ByA3UnlessPreviousIsA5(), 1, 1)
                );
    }

    private Rule[] rules () {
        return new Rule[]{
                rule(),
                new RuleIdentity()
        };
    }
}
