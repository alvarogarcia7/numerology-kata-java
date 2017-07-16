package com.example.kata.rules.gaslimited;

public interface GasConsuming {
    void refuel();

    void consume();

    boolean hasGas();
}
