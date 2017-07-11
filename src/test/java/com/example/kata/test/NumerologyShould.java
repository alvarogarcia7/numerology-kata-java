package com.example.kata.test;

import com.example.kata.numerology.Numerology;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NumerologyShould {
    @Test
    public void replace_a_single_nine_by_two_tens() {
        assertThat(replace(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), is(asList(1, 2, 3, 4, 5, 6, 7, 8, 10, 10, 10)));
    }

    @Test
    public void replace_multiple_nines_by_two_tens_each() {
        assertThat(replace(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), is(asList(1, 2, 3, 4, 5, 6, 7, 8, 10, 10, 10)));
        assertThat(replace(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 9)), is(asList(1, 2, 3, 4, 5, 6, 7, 8, 10, 10, 10, 10)));
    }

    @Test
    public void replace_no_nines_by_two_tens_each() {
        assertThat(replace(asList(1, 2, 3, 4, 5, 6, 7, 8)), is(asList(1, 2, 3, 4, 5, 6, 7, 8)));
    }

    private List<Integer> replace(List<Integer> input) {
        return new Numerology().replace(input);
    }
}
