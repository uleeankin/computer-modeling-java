package com.uleeankin.computermodelingjava.lab6;

import javafx.beans.property.SimpleDoubleProperty;

public class ExperimentResult {

    private SimpleDoubleProperty p1;
    private SimpleDoubleProperty p2;
    private SimpleDoubleProperty p3;
    private SimpleDoubleProperty p4;
    private SimpleDoubleProperty p5;
    private SimpleDoubleProperty p6;
    private SimpleDoubleProperty p7;
    private SimpleDoubleProperty p8;
    private SimpleDoubleProperty p9;
    private SimpleDoubleProperty p10;

    public ExperimentResult(double p1, double p2, double p3,
                            double p4, double p5, double p6,
                            double p7, double p8, double p9,
                            double p10) {
        this.p1 = new SimpleDoubleProperty(p1);
        this.p2 = new SimpleDoubleProperty(p2);
        this.p3 = new SimpleDoubleProperty(p3);
        this.p4 = new SimpleDoubleProperty(p4);
        this.p5 = new SimpleDoubleProperty(p5);
        this.p6 = new SimpleDoubleProperty(p6);
        this.p7 = new SimpleDoubleProperty(p7);
        this.p8 = new SimpleDoubleProperty(p8);
        this.p9 = new SimpleDoubleProperty(p9);
        this.p10 = new SimpleDoubleProperty(p10);
    }

    public double getP1() {
        return p1.get();
    }

    public void setP1(double p1) {
        this.p1.set(p1);
    }

    public double getP2() {
        return p2.get();
    }

    public void setP2(double p2) {
        this.p2.set(p2);
    }

    public double getP3() {
        return p3.get();
    }

    public void setP3(double p3) {
        this.p3.set(p3);
    }

    public double getP4() {
        return p4.get();
    }

    public void setP4(double p4) {
        this.p4.set(p4);
    }

    public double getP5() {
        return p5.get();
    }

    public void setP5(double p5) {
        this.p5.set(p5);
    }

    public double getP6() {
        return p6.get();
    }

    public void setP6(double p6) {
        this.p6.set(p6);
    }

    public double getP7() {
        return p7.get();
    }

    public void setP7(double p7) {
        this.p7.set(p7);
    }

    public double getP8() {
        return p8.get();
    }

    public void setP8(double p8) {
        this.p8.set(p8);
    }

    public double getP9() {
        return p9.get();
    }

    public void setP9(double p9) {
        this.p9.set(p9);
    }

    public double getP10() {
        return p10.get();
    }

    public void setP10(double p10) {
        this.p10.set(p10);
    }
}
