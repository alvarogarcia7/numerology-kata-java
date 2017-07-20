package com.example.kata.test.unit.rule;

import com.example.kata.numerology.rules.Rule;
import com.example.kata.rules.LimitedGasRule;
import com.example.kata.rules.RuleReplaceA3ByA5UnlessNextIsA5;
import com.example.kata.rules.RuleReplaceA4ByA3UnlessPreviousIsA5;
import com.example.kata.rules.RulesThatEnableOtherRules;
import io.vavr.control.Option;
import org.junit.Test;

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
    public void apply_the_rule_to_a_the_first_element_in_a_valid_case () {
        assertThat(apply(rule()).to(asList(3, 0, 4)).at(0), is(Option.of(asList(5))));
    }

    private Rule rule () {
        return new RulesThatEnableOtherRules(
                new LimitedGasRule(
                        new RuleReplaceA3ByA5UnlessNextIsA5(), 1, 1),
                new LimitedGasRule(
                        new RuleReplaceA4ByA3UnlessPreviousIsA5(), 1, 1));
    }
}
