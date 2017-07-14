package com.example.kata.test.unit;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.RuleReplace2ForAnEqualAmountOfNumbersToTheLeft;
import com.example.kata.numerology.rules.Rule9ForTwo10s;
import com.example.kata.numerology.rules.RuleReplace6ForAsMany3AsTheValueToTheNthRight;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NumerologyShould {

    //Rule number 1
    @Test
    public void rule_1_cannot_access_a_nonexisting_index() {
        assertThat(rule1(asList(2), 1), is(Optional.empty()));
    }

    @Test
    public void replace_a_single_nine_by_two_tens() {
        assertThat(rule1(asList(9), 0), is(Optional.of(asList(10, 10))));
    }

    @Test
    public void replace_no_nines_by_two_tens_each() {
        assertThat(rule1(asList(1), 0), is(Optional.empty()));
    }

    //Rule number 2
    @Test
    public void replace_the_number_previous_to_a_2_for_as_many_ones() {
        assertThat(rule2(asList(8, 2), 1), is(Optional.of(asList(1, 1, 1, 1, 1, 1, 1, 1))));
    }

    @Test
    public void rule_2_cannot_access_a_nonexisting_index() {
        assertThat(rule2(asList(2), 1), is(Optional.empty()));
    }

    @Test
    public void replace_the_number_previous_to_a_2_for_as_many_ones_but_its_not_possible() {
        assertThat(rule2(asList(2), 0), is(Optional.empty()));
    }


    //Rule number 3
    @Test
    public void not_apply_rule_3_to_numbers_that_not_match_the_specification() {
        assertThat(rule3(asList(2), 0), is(Optional.empty()));
    }


    @Test
    public void rule_3_cannot_access_a_nonexisting_index() {
        assertThat(rule3(asList(2), 1), is(Optional.empty()));
    }

    @Test
    public void replace_rule_3() {
        assertThat(rule3(asList(1, 6, 3), 1), is(Optional.of(asList(3, 3, 3))));
    }

    @Test
    public void replace_rule_3_cant_because_of_the_left() {
        assertThat(rule3(asList(6), 0), is(Optional.empty()));
    }

    @Test
    public void replace_rule_3_cant_because_of_the_right() {
        assertThat(rule3(asList(4, 6, 3, 4, 5), 1), is(Optional.empty()));
        assertThat(rule3(asList(1, 6), 1), is(Optional.empty()));
    }

    private Optional<List<Integer>> rule1(List<Integer> input, int index) {
        return new Rule9ForTwo10s().apply(new Elements(input), index);
    }

    private Optional<List<Integer>> rule2(List<Integer> input, int index) {
        return new RuleReplace2ForAnEqualAmountOfNumbersToTheLeft().apply(Elements.in(input), index);
    }

    private Optional<List<Integer>> rule3(List<Integer> integers, int index) {
        return new RuleReplace6ForAsMany3AsTheValueToTheNthRight().apply(Elements.in(integers), index);
    }
}
