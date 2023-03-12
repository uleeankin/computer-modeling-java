package com.uleeankin.computermodelingjava.beta;

public class BetaParametersCalculator {

    private final int m;

    private final double p;

    public BetaParametersCalculator(int m, double p) {
        this.m = m;
        this.p = p;
    }

    public double getMathDelay() {
        return this.p / (this.p + this.m);
    }

    public double getDispersion() {
        return this.p * this.m
                / (Math.pow(this.p + this.m, 2)
                    * (this.p + this.m + 1));
    }
}
