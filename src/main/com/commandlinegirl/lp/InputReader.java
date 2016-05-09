package com.commandlinegirl.lp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {

    private static final String DELIMINATOR = ",";

    List<List<Double>> readInput(String fileName) {
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

    List<Double> convertToDouble(List<String> strings) {
        return strings.stream().map(Double::new).collect(Collectors.toList());
    }

}
