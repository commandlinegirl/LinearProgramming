package com.commandlinegirl.lp.solvers;


import com.commandlinegirl.lp.Objective;
import com.commandlinegirl.lp.OptimizationType;
import com.commandlinegirl.lp.Tableaux;
import com.commandlinegirl.lp.Validator;

public class LinearSolver implements Solver {

    private final OptimizationType type;

    public LinearSolver(OptimizationType type) {
        this.type = type;
    }

    @Override
    public Objective solve(Tableaux tableaux) {
        Validator validator = new Validator();
        //if (validator.isFeasible(tableaux)) throw new InfeasibleException();
        //if (validator.isBounded(tableaux)) throw new UnboundedException();
        int loop = 0;

        double[] objectiveFunction = tableaux.getMatrix()[0];

        while (foundNegativeCoeficients(objectiveFunction) && loop < 100) {
            int pivotColumn =  tableaux.getPivotColumn();
            if (tableaux.getRow(0)[pivotColumn] >= 0) {
                return new Objective(tableaux); // optimal solution
            }
            int pivotRow = tableaux.getPivotRow(pivotColumn);
            System.out.println(pivotColumn + " " + pivotRow);
            tableaux.pivot(pivotRow, pivotColumn);
            loop++;
        }

        return new Objective(tableaux);
    }

    private boolean foundNegativeCoeficients(double[] objective) {
        for (double d : objective) {
            if (d < 0)
                return true;
        }
        return false;
    }

}
