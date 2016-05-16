package com.commandlinegirl.lp;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

public class Tableau {

    private static final double EPSILON = 1.0e-6;
    private final double[][] matrix;

    private static final NumberFormat formatter = new DecimalFormat("#0.0");

    public Tableau(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    /** Return the index of the left-most negative variable in a list **/
    public int getPivotColumn() {
        double[] objective = matrix[0];
        for (int i = 0; i < objective.length; i++) {
            if (objective[i] < 0) {
                return i;
            }
        }
        return -1;
    }

    /** Finds the index of the pivot row (leaving basic variable) by applying
     * the minimum ratio test. **/
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

                //Bland's Rule: in case of a tie, pick up the row with a lower index
                if (Math.abs(ratio - minValue) < EPSILON) {
                    continue;
                }
                else if (ratio < minValue) {
                    minValue = ratio;
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    /** Returns the nth column of the matrix **/
    private double[] getColumn(int columnIndex) {
        //TODO: Precondition
        double[] column = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][columnIndex];
        }
        return column;
    }

    /** Returns the nth row of the matrix **/
    public double[] getRow(int rowIndex) {
        //TODO: Precondition
        return matrix[rowIndex];
    }

    public void pivot(int row, int col) {
        double pivotValue = matrix[row][col];
        assert(pivotValue > 0);
        divideRowBy(row, pivotValue);

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

    public void divideRowBy(int i, Double value) {
        double[] row = matrix[i];
        for (int j = 0; j < row.length; j++) {
            row[j] = row[j] / value;
        }
    }

    /** Returns a copy of constraint rows of the matrix **/
    public double[][] getConstraints() {
        return Arrays.copyOfRange(matrix, 1, matrix.length);
    }

    /** Returns true if the model is unbounded **/
    public boolean isUnbounded() {
        return false;
    }

    /** Returns true if the problem is infeasible **/
    public boolean isInfeasible() {
        return false;
    }

    public void print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(formatter.format(matrix[i][j]) + " ");
            }
            System.out.println();
        }
    }

}
