package com.commandlinegirl.lp.models;


import com.commandlinegirl.lp.Objective;
import com.commandlinegirl.lp.maths.SimpleMatrix;

import java.util.List;
import java.util.stream.Collectors;


public class LinearSolver implements Solver {

    private boolean maximization = true;

    @Override
    public Objective solve(Objective objective, SimpleMatrix equations) {
        int[] pivot = getPivot(objective, equations);
        System.out.println(pivot[0] + " " + pivot[1]);
        Double pivotValue = equations.getMatrix().get(pivot[0]).get(pivot[1]);
        List<Double> row1 = equations.getMatrix().get(pivot[0]).stream().map(i -> i / pivotValue).collect(Collectors.toList());
        return null;
    }

    private int[] getPivot(Objective objective, SimpleMatrix equations) {
        int pivotColumn =  objective.getMinIndex();
        int pivotRow = equations.getPivotRow(pivotColumn);
        return new int[]{pivotRow, pivotColumn};
    }

}
