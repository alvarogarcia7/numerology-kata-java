package com.example.kata.rules;

import com.example.kata.numerology.rules.Rule;

public class LimitedGasRule extends GasConsumingRule {
    private final int maximumGas;
    private int consumedGas;

    public LimitedGasRule(Rule rule, int gas, int maximumGas) {
        super(rule, gas);
        this.maximumGas = maximumGas;
        this.consumedGas = 0;
    }

    @Override
    protected void consumeGas() {
        this.consumedGas++;
        super.consumeGas();
    }

    @Override
    protected void refuel() {
        if (this.consumedGas >= maximumGas) {

        } else {
            super.refuel();
        }
    }
}
