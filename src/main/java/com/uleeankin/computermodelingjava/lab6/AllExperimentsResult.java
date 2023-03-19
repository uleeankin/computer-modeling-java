package com.uleeankin.computermodelingjava.lab6;

import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
import java.util.List;

public class AllExperimentsResult {

    private SimpleDoubleProperty pa;
    private SimpleDoubleProperty pb;
    private SimpleDoubleProperty pc;
    private SimpleDoubleProperty pd;

    public AllExperimentsResult(double pa, double pb, double pc,
                            double pd) {
        this.pa = new SimpleDoubleProperty(pa);
        this.pb = new SimpleDoubleProperty(pb);
        this.pc = new SimpleDoubleProperty(pc);
        this.pd = new SimpleDoubleProperty(pd);
    }

    public List<Double> toList() {
        List<Double> list = new ArrayList<>();

        list.add(this.getPa());
        list.add(this.getPb());
        list.add(this.getPc());
        list.add(this.getPd());

        return list;
    }

    public double getPa() {
        return pa.get();
    }

    public void setPa(double pa) {
        this.pa.set(pa);
    }

    public double getPb() {
        return pb.get();
    }

    public void setPb(double pb) {
        this.pb.set(pb);
    }

    public double getPc() {
        return pc.get();
    }

    public void setPc(double pc) {
        this.pc.set(pc);
    }

    public double getPd() {
        return pd.get();
    }

    public void setPd(double pd) {
        this.pd.set(pd);
    }
}
