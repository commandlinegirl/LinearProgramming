package com.commandlinegirl.lp;


import com.commandlinegirl.lp.solvers.LinearSolver;
import com.commandlinegirl.lp.solvers.Solver;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class SimplexMethod {

    private static final Logger logger = Logger.getLogger(SimplexMethod.class.getName());

    public static double[] convertDoubles(List<Double> in) {
        if (in == null)
            return new double[] {};
        double[] doubles = new double[in.size()];
        Iterator<Double> iterator = in.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            doubles[i] = iterator.next();
            i++;
        }
        return doubles;
    }

    public static double[][] convertDoublesMatrix(List<List<Double>> in) {
        if (in == null || in.size() == 0 || in.get(0).size() == 0)
            return new double[][] {};
        double[][] doubles = new double[in.size()][in.get(0).size()];
        for (int i = 0; i < in.size(); i++) {
            doubles[i] = convertDoubles(in.get(i));
        }
        return doubles;
    }

    public static void main(String... args) {
        if (args.length == 0) {
            logger.severe("Usage: java SimplexMethod <name-of_file>");
            System.exit(0);
        }

        InputReader freader = new InputReader();
        List<List<Double>> input = freader.readInput(args[0]);

        double[][] converted = convertDoublesMatrix(input);
        Tableaux tableaux = new Tableaux(converted);

        Solver solver = new LinearSolver(OptimizationType.MAX);
        Objective result = solver.solve(tableaux);
        System.out.println(result.getSolution());

    }

}
