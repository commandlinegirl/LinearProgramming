package com.commandlinegirl.lp;

/**
 * Created on 13/05/16.
 */
public class Tableaux {

    private static final double EPSILON = 1.0e-6;
    private final double[][] matrix;

    public Tableaux(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    /** Return the index of the lowest value in a list **/
    public int getPivotColumn() {
        double[] objective = matrix[0];
        int minIndex = 0;
        double minValue = Double.MAX_VALUE;
        for (int i = 0; i < objective.length; i++) {
            if (objective[i] < minValue) {
                minValue = objective[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public int getPivotRow(int pivotColumnIndex) {
        double minValue = Double.MAX_VALUE;
        int minIndex = 0;
        double[] rhsColumn = getColumn(matrix[0].length - 1);
        double[] pivotColumn = getColumn(pivotColumnIndex);
        for (int i = 1; i < rhsColumn.length; i++) {
            double rhs = rhsColumn[i];
            double pivot = pivotColumn[i];
            if (Math.abs(pivot - 0.0) > EPSILON) {
                double ratio = rhs / pivot;
                //TODO: handle the case of equal ratios for different i
                if (ratio < minValue) {
                    minValue = ratio;
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    private double[] getColumn(int columnIndex) {
        //TODO: Precondition
        double[] column = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][columnIndex];
        }
        return column;
    }

    public double[] getRow(int rowIndex) {
        //TODO: Precondition
        return matrix[rowIndex];
    }

    public void divideRowBy(int i, Double value) {
        double[] row = matrix[i];
        for (int j = 0; j < row.length; j++) {
            row[j] = row[j] / value;
        }
    }



    public void pivot(int row, int col) {

        double pivotValue = matrix[row][col];
        assert(pivotValue>0);
        divideRowBy(row, pivotValue);
        //assert( equal(tab->mat[row][col], 1. ));

        for(int i = 0; i < matrix.length; i++) {
            if (i != row) {
                double multiplier = matrix[i][col];
                subtractRows(matrix[i], matrix[row], multiplier);
            }
        }
    }

    private void subtractRows(double[] row, double pivotRow[], double multiplier) {
        for(int i = 0; i < row.length; i++) { // r[i] = r[i] - z * r[row];
            row[i] -= multiplier * pivotRow[i];
        }
    }
}
