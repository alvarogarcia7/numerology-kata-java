package com.example.kata.rules.gaslimited;

public class Gas implements GasConsuming {
    private int availableGas;

    public Gas(int availableGas) {
        this.availableGas = availableGas;
    }

    @Override
    public void refuel(){
        this.availableGas++;
    }

    @Override
    public void consume(){
        this.availableGas--;
    }

    @Override
    public boolean hasGas(){
        return availableGas >0;
    }
}
