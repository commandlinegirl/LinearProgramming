package com.commandlinegirl.lp;

import java.util.Arrays;

public class Objective {

    private final Tableau tableau;

    public Objective(Tableau tableau) {
        this.tableau = tableau;
    }

    public double[] getSolution() {
        int columnCount = tableau.getMatrix()[0].length;
        double[] solution = new double[columnCount];
        double[] rhs = tableau.getColumn(columnCount - 1);
        Arrays.fill(solution, 0);
        int[] basicVariableIndices = tableau.getBasicVariables();
        for (int i = 0; i < basicVariableIndices.length; i++) {
            solution[basicVariableIndices[i]] = rhs[i];
        }
        return solution;
    }

    public Tableau getTableau() {
        return tableau;
    }
}
