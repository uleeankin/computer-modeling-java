package com.uleeankin.computermodelingjava.lab6;

import java.util.stream.LongStream;

public class BernullyFormulaCalculator {

    public static double bernullyFormula(int m, int n, double p) {
        return getC(m, n) * Math.pow(p, m) * Math.pow(1 - p, n - m);
    }

    private static double getC(int m, int n) {
        return (double) factorial(n) / (factorial(m) * factorial(n - m));
    }

    private static long factorial(int n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (long x, long y) -> x * y);
    }
}
