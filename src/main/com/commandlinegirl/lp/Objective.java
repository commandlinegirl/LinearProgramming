package com.commandlinegirl.lp;

public class Objective {

    private final Tableau tableau;

    public Objective(Tableau tableau) {
        this.tableau = tableau;
    }

    public double[] getSolution() {
        return tableau.getMatrix()[0];
    }

    public Tableau getTableau() {
        return tableau;
    }
}
