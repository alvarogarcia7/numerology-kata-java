package com.example.kata.test.unit.rule;

import com.example.kata.numerology.LimitedGasTank;
import com.example.kata.numerology.rules.Rule;
import com.example.kata.rules.GasTank;
import com.example.kata.rules.RuleReplaceA3ByA5UnlessNextIsA5;
import com.example.kata.rules.RuleReplaceA4ByA3UnlessPreviousIsA5;
import com.example.kata.rules.RulesThatEnableOtherRules;
import io.vavr.control.Option;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        assertThat(applyingAllRules(rules()).to(flattened(inputPair())), is(outputPair()));
    }

    @Test
    public void apply_all_rules_to_two_pairs () {
        List<Integer> to = applyingAllRules(rules()).to(flattened(inputPair(), inputPair()));
        assertThat(to, is(flattened(outputPair(), outputPair())));
    }

    List<Integer> outputPair () {
        return asList(5, 3);
    }

    private List<Integer> flattened (final List<Integer>... inputs) {
        List<Integer> result = Arrays.stream(inputs).flatMap(Collection::stream).collect(Collectors.toList());
        return result;
    }


    private List<Integer> inputPair () {
        return asList(3, 4);
    }

    private Rule rule () {
        return new RulesThatEnableOtherRules(
                        new RuleReplaceA3ByA5UnlessNextIsA5(),
                        new RuleReplaceA4ByA3UnlessPreviousIsA5(),
                                new LimitedGasTank(2, 2),
                                new LimitedGasTank(2, 2));

    }

    private Rule[] rules () {
        return new Rule[]{
                rule(),
        };
    }
}
