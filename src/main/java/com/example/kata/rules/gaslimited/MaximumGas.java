package com.example.kata.rules.gaslimited;

public class MaximumGas extends Gas {
    private final int maximum;
    private int consumed;

    public MaximumGas(int available, int maximum) {
        super(available);
        this.maximum = maximum;
        this.consumed = 0;
    }


    @Override
    public void refuel() {
        if(this.consumed >= maximum) {
            super.refuel();
        }
    }

    @Override
    public void consume() {
        this.consumed++;
        super.consume();
    }
}
