package com.example.kata.rules;

import com.example.kata.numerology.Elements;
import com.example.kata.numerology.rules.Rule;
import io.vavr.control.Option;

import java.util.List;

public class Rule4And5 implements Rule {
    private final Rule rule1;
    private final Rule rule2;
    private int numberOfTimesForRule1;
    private int numberOfTimesForRule2;

    public Rule4And5(Rule rule1, Rule rule2) {
        this.rule1 = rule1;
        this.rule2 = rule2;
        numberOfTimesForRule1 = 0;
        numberOfTimesForRule2 = 0;
    }

    @Override
    public Option<List<Integer>> apply(Elements elements, int index) {
        //calculate state of rules until now
        for(int i=0;i<index;i++){
            if (numberOfTimesForRule1 <= numberOfTimesForRule2 && (rule1.apply(elements, i).isDefined())) {
                numberOfTimesForRule1++;
                continue;
            }
            if (numberOfTimesForRule2 <= numberOfTimesForRule1 && (rule2.apply(elements, i).isDefined())) {
                numberOfTimesForRule2++;
                continue;
            }
        }

        if(numberOfTimesForRule1 <= numberOfTimesForRule2 && rule1.apply(elements, index).isDefined()){
            return rule1.apply(elements, index);
        }
        if(numberOfTimesForRule2 <= numberOfTimesForRule1 && rule2.apply(elements, index).isDefined()){
            return rule2.apply(elements, index);
        }

        return Option.none();
    }
}
