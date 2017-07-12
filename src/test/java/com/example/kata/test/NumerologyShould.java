package com.example.kata.test;

import com.example.kata.numerology.Numerology;
import com.example.kata.numerology.NumerologyWithRules;
import com.example.kata.numerology.RuleReplace2ForAnEqualAmountOfNumbersToTheLeft;
import com.example.kata.numerology.rules.Rule9ForTwo10s;
import com.example.kata.numerology.rules.RuleReplace6ForAsMany3AsTheValueToTheNthRight;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NumerologyShould {
    @Test
    public void replace_a_single_nine_by_two_tens() {
        assertThat(replace(asList(1, 3, 4, 5, 7, 8, 9, 10)), is(asList(1, 3, 4, 5, 7, 8, 10, 10, 10)));
    }

    @Test
    public void replace_multiple_nines_by_two_tens_each() {
        assertThat(replace(asList(1, 3, 4, 5, 7, 8, 9, 10)), is(asList(1, 3, 4, 5, 7, 8, 10, 10, 10)));
        assertThat(replace(asList(1, 3, 4, 5, 7, 8, 9, 9)), is(asList(1, 3, 4, 5, 7, 8, 10, 10, 10, 10)));
    }

    @Test
    public void replace_no_nines_by_two_tens_each() {
        assertThat(replace(asList(1, 3, 4, 5, 7, 8)), is(asList(1, 3, 4, 5, 7, 8)));
    }


    @Test
    public void replace_the_number_previous_to_a_2_for_as_many_ones() {
        assertThat(replace(asList(8, 2)), is(asList(8, 1, 1, 1, 1, 1, 1, 1, 1)));
    }

    @Test
    public void replace_the_number_previous_to_a_2_for_as_many_ones_but_its_not_possible() {
        assertThat(replace(asList(2)), is(asList(2)));
    }


    @Test
    public void replace_rule_3() {
        assertThat(replace(asList(1, 6, 3, 4, 5)), is(asList(1, 3, 3, 3, 3, 4, 5)));
    }

    @Test
    public void replace_rule_3_cant_because_of_the_left() {
        assertThat(replace(asList(6, 3, 4, 5)), is(asList(6, 3, 4, 5)));
    }

    @Test
    public void replace_rule_3_cant_because_of_the_right() {
        assertThat(replace(asList(4, 6, 3, 4, 5)), is(asList(4, 6, 3, 4, 5)));
        assertThat(replace(asList(1, 6)), is(asList(1, 6)));
    }

    private List<Integer> replace(List<Integer> input) {
        List<Integer> replace = new Numerology().replace(input);
        List<Integer> replace1 = new NumerologyWithRules(new Rule9ForTwo10s(), new RuleReplace2ForAnEqualAmountOfNumbersToTheLeft(), new RuleReplace6ForAsMany3AsTheValueToTheNthRight()).replace(input);
        assertThat(replace, is(replace1));
        return replace;
    }
}
