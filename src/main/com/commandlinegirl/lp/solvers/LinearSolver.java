package com.commandlinegirl.lp.solvers;


import com.commandlinegirl.lp.*;
import com.commandlinegirl.lp.exceptions.InfeasibleException;
import com.commandlinegirl.lp.exceptions.UnboundedException;
import com.google.common.base.Preconditions;

import java.util.logging.Logger;

public class LinearSolver implements Solver {

    private static final Logger logger = Logger.getLogger(LinearSolver.class.getName());

    private final OptimizationType type;

    public LinearSolver(OptimizationType type) {
        this.type = type;
    }

    @Override
    public Objective solve(Tableau tableau) throws InfeasibleException, UnboundedException {
        if (!validate(tableau)) throw new IllegalArgumentException("Tableau is not in proper form");

        // Check if the problem has a solution
        if (tableau.isInfeasible()) throw new InfeasibleException("Problem is infeasible.");
        if (tableau.isUnbounded()) throw new UnboundedException("Problem is unbounded.");

        // Optimize
        int loop = 0;
        double[] objectiveFunction = tableau.getMatrix()[0];
        while (!isOptimal(objectiveFunction) && loop < 100) {
            int pivotColumn =  tableau.getPivotColumn(); // entering variable
            int pivotRow = tableau.getPivotRow(pivotColumn); // leaving variable
            tableau.pivot(pivotRow, pivotColumn);
            loop++;
        }

        return new Objective(tableau);
    }

    @Override
    public boolean validate(Tableau tableau) {
        Preconditions.checkNotNull(tableau, "Tableau cannot be null.");
        return tableau.inProperForm();
    }

    /** Returns true if the current solution is optimal by verifying
     * if no entering basic variable is available, ie. there are no
     * negative values in the objective function */
    private boolean isOptimal(double[] objective) {
        for (double d : objective) {
            if (d < 0)
                return false;
        }
        return true;
    }



}
