package com.commandlinegirl.lp;


import com.commandlinegirl.lp.maths.SimpleMatrix;
import com.commandlinegirl.lp.models.LinearSolver;
import com.commandlinegirl.lp.models.Solver;

import java.util.List;
import java.util.logging.Logger;

public class SimplexMethod {

    private static final Logger logger = Logger.getLogger(SimplexMethod.class.getName());

    public static void main(String... args) {
        if (args.length == 0) {
            logger.severe("Usage: java SimplexMethod <name-of_file>");
            System.exit(0);
        }

        InputReader freader = new InputReader();
        List<List<Double>> tableau = freader.readInput(args[0]);

        Objective objective = new Objective(tableau.get(0));
        SimpleMatrix equations = new SimpleMatrix(tableau.subList(1, tableau.size()));

        Solver solver = new LinearSolver();
        Objective result = solver.solve(objective, equations);

    }
}
