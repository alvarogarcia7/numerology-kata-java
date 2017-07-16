package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

public class Rule4And5 implements Rule {
    private final Rule rule1;
    private final Rule rule2;
    private int previousApplicationsOfRule1;
    private int previousApplicationsOfRule2;

    public Rule4And5(Rule rule1, Rule rule2) {
        this.rule1 = rule1;
        this.rule2 = rule2;
        previousApplicationsOfRule1 = 0;
        previousApplicationsOfRule2 = 0;
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        //calculate state of rules until now
        for (int i = 0; i < index; i++) {
            if (isRule1Applicable() && (rule1AppliesTo(elements, i))) {
                previousApplicationsOfRule1++;
                continue;
            }
            if (isRule2Applicable() && rule2AppliesTo(elements, i)) {
                previousApplicationsOfRule2++;
                continue;
            }
        }

        if (isRule1Applicable() && rule1AppliesTo(elements, index)) {
            return rule1.apply(elements, index);
        }
        if (isRule2Applicable() && rule2AppliesTo(elements, index)) {
            return rule2.apply(elements, index);
        }

        return Option.none();
    }

    boolean rule2AppliesTo(Elements elements, int i) {
        return rule2.apply(elements, i).isDefined();
    }

    boolean rule1AppliesTo(Elements elements, int index) {
        return rule1.apply(elements, index).isDefined();
    }

    boolean isRule2Applicable() {
        return previousApplicationsOfRule2 <= previousApplicationsOfRule1;
    }

    boolean isRule1Applicable() {
        return previousApplicationsOfRule1 <= previousApplicationsOfRule2;
    }
}
