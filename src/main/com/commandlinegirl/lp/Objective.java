package com.commandlinegirl.lp;


public class Objective {

    private final Tableaux tableaux;
    private double[] solution;

    public Objective(Tableaux tableaux) {
        this.tableaux = tableaux;
    }

    public double[][] getSolution() {
        return tableaux.getMatrix();
    }
}
