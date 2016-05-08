package com.commandlinegirl.lp;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimplexMethod {

    private static final Logger logger = Logger.getLogger(SimplexMethod.class.getName());

    private int getMinIndex(List<Integer> list) {
        int minIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < list.get(minIndex))
                minIndex = i;
        }
        return minIndex;
    }

    private int[] getPivot(List<List<Integer>> tableau) {
        int pivotColumn =  getMinIndex(tableau.get(0));
        int pivotRow = getPivotRow(tableau, pivotColumn);
        return new int[]{pivotRow, pivotColumn};
    }

    private List<List<Integer>> removeRowFromTableau(List<List<Integer>> tableau, int rowIndex) {
        if (Preconditions.checkIndexNotWithinArray(tableau, rowIndex))
            throw new IllegalArgumentException("Invalid row index " + rowIndex + " for tableau " + tableau);
        List<List<Integer>> result = new ArrayList<>();
        IntStream.range(0, tableau.size()).forEach(i -> {
           if (i != rowIndex)
               result.add(tableau.get(i));
        });
        return result;
    }

    private int getPivotRow(List<List<Integer>> tableau, int pivotColumnIndex) {
        double minValue = 0;
        int minIndex = 0;
        List<List<Integer>> tableauNoObj = removeRowFromTableau(tableau, 0);
        List<Integer> valueColumn = getColumn(tableauNoObj, tableauNoObj.get(0).size() - 1);
        List<Integer> pivotColumn = getColumn(tableauNoObj, pivotColumnIndex);
        for (int i = 0; i < valueColumn.size(); i++) {
            if (pivotColumn.get(i) != 0 && valueColumn.get(i) / pivotColumn.get(i) < minValue) {
                minValue = valueColumn.get(i) / pivotColumn.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }

    private List<Integer> getColumn(List<List<Integer>> tableau, int columnIndex) {
        return tableau.stream().map(e -> e.get(columnIndex)).collect(Collectors.toList());
    }

    private void maximize(List<List<Integer>> tableau) {
        int[] pivot = getPivot(tableau);
        System.out.println(pivot[0] + " " + pivot[1]);

    }

    public static void main(String... args) {
        if (args.length == 0) {
            logger.severe("Usage: java SimplexMethod <name-of_file>");
            System.exit(0);
        }

        SimplexMethod simplex = new SimplexMethod();
        InputReader freader = new InputReader();
        List<List<Integer>> tableau = freader.readInput(args[0]);
        simplex.maximize(tableau);
    }
}
