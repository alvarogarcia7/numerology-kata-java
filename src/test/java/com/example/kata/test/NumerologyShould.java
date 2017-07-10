package com.example.kata.test;

import com.example.kata.numerology.Numerology;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;

public class NumerologyShould {
    @Test
    public void replace_a_single_nine_by_two_tens() {
        assertThat(new Numerology().replace(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), org.hamcrest.Matchers.is(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 10, 10, 10)));
    }

    @Test
    public void replace_multiple_nines_by_two_tens_each() {assertThat(new Numerology().replace(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), org.hamcrest.Matchers.is(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 10, 10, 10)));
        assertThat(new Numerology().replace(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 9)), org.hamcrest.Matchers.is(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 10, 10, 10, 10)));
    }
}
