package com.commandlinegirl.lp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {

    private static final String DELIMINATOR = ",";

    public List<List<Double>> readInput(String fileName) {
        File file = new File(fileName);
        List<List<Double>> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Double> equation = convertToDouble(Arrays.asList(line.split(DELIMINATOR)));
                input.add(equation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public List<Double> convertToDouble(List<String> strings) {
        return strings.stream().map(Double::new).collect(Collectors.toList());
    }

    public double[] convertDoubles(List<Double> in) {
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

    public double[][] convertDoublesMatrix(List<List<Double>> in) {
        if (in == null || in.size() == 0 || in.get(0).size() == 0)
            return new double[][] {};
        double[][] doubles = new double[in.size()][in.get(0).size()];
        for (int i = 0; i < in.size(); i++) {
            doubles[i] = convertDoubles(in.get(i));
        }
        return doubles;
    }
}
