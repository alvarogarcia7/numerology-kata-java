package com.example.kata.numerology;

import com.example.kata.rules.GasTank;

public class LimitedGasTank extends GasTank {
    private final int maximumGas;
    private final int refueledGas;
    private final int remainingGas;

    public LimitedGasTank (final int initialGas, final int maximumGas) {
        this(maximumGas, 0, initialGas);
    }

    private LimitedGasTank (final int maximumGas, final int refueledGas, final int remainingGas) {
        super(remainingGas);
        this.maximumGas = maximumGas;
        this.refueledGas = refueledGas;
        this.remainingGas = remainingGas;
    }

    @Override
    public boolean isEmpty () {
        return super.isEmpty();
    }

    @Override
    public GasTank consume () {
        if(this.remainingGas <= 0){
            return null;
        }
        return new LimitedGasTank(maximumGas, refueledGas, remainingGas - 1);
    }

    @Override
    public GasTank refuel () {
        if (this.refueledGas >= maximumGas) {
            return null;
        }
        return new LimitedGasTank(maximumGas, refueledGas + 1, remainingGas);
    }

    protected GasTank clone_ ()  {
        return new LimitedGasTank(maximumGas, refueledGas, remainingGas);
    }
}
