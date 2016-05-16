package com.commandlinegirl.lp;


import com.commandlinegirl.lp.exceptions.InfeasibleException;
import com.commandlinegirl.lp.exceptions.UnboundedException;
import com.commandlinegirl.lp.solvers.LinearSolver;
import com.commandlinegirl.lp.solvers.Solver;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class SimplexMethod {

    private static final Logger logger = Logger.getLogger(SimplexMethod.class.getName());

    public static void main(String... args) throws InfeasibleException, UnboundedException {
        if (args.length == 0) {
            logger.severe("Usage: java SimplexMethod <name-of_file>");
            System.exit(0);
        }

        InputReader freader = new InputReader();
        List<List<Double>> input = freader.readInput(args[0]);

        double[][] converted = freader.convertDoublesMatrix(input);
        Tableau tableau = new Tableau(converted);
        System.out.println("Input tableau: ");
        tableau.print();

        Solver solver = new LinearSolver(OptimizationType.MAX);
        Objective result = solver.solve(tableau);
        System.out.println("Output tableau: ");
        result.getTableau().print();

        System.out.println("Solution: ");
        System.out.println(Arrays.toString(result.getSolution()));
    }

}
