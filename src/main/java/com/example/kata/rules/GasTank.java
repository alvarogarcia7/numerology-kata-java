package com.example.kata.rules;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class GasTank {
    private final int gas;

    public GasTank (final int initialGas) {
        this.gas = initialGas;
    }

    public boolean isEmpty () {
        return gas <= 0;
    }

    public GasTank consume () {
        return new GasTank(gas-1);
    }

    public GasTank refuel () {
        return new GasTank(this.gas + 1);
    }

    protected GasTank clone_ ()  {
        return new GasTank(gas);
    }
}
