package com.uleeankin.computermodelingjava.lab6;

public record ConfidenceInterval(double positive, double negative) {

    public double getPositive() {
        return positive;
    }

    public double getNegative() {
        return negative;
    }
}
