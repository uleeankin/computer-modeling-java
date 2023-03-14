package com.uleeankin.computermodelingjava.lab6;

public class TheoreticalProbabilityCalculator {

    public double getTheoreticalAProbability(double p) {
        return 1d - BernullyFormulaCalculator.bernullyFormula(0, 10, p);
    }

    public double getTheoreticalBProbability(double p) {
        return BernullyFormulaCalculator.bernullyFormula(1, 10, p);
    }

    public double getTheoreticalCProbability(double p) {
        return BernullyFormulaCalculator.bernullyFormula(2, 10, p);
    }

    public double getTheoreticalDProbability(double p) {
        return 1d - (BernullyFormulaCalculator.bernullyFormula(0, 10, p)
                + BernullyFormulaCalculator.bernullyFormula(1, 10, p));
    }
}
