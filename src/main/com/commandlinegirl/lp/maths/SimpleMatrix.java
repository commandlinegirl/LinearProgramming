package com.commandlinegirl.lp.maths;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleMatrix {

    private final List<List<Double>> matrix;

    public SimpleMatrix(List<List<Double>> matrix) {
        this.matrix = matrix;
    }

    public List<List<Double>> getMatrix() {
        return Collections.unmodifiableList(matrix);
    }

    public int getPivotRow(int pivotColumnIndex) {
        double minValue = 0;
        int minIndex = 0;
        List<Double> valueColumn = getColumn(matrix.get(0).size() - 1);
        List<Double> pivotColumn = getColumn(pivotColumnIndex);
        for (int i = 0; i < valueColumn.size(); i++) {
            if (pivotColumn.get(i) != 0 && valueColumn.get(i) / pivotColumn.get(i) < minValue) {
                minValue = valueColumn.get(i) / pivotColumn.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }

    private List<Double> getColumn(int columnIndex) {
        return matrix.stream().map(e -> e.get(columnIndex)).collect(Collectors.toList());
    }

}
