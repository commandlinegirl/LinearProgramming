package com.commandlinegirl.lp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 05/05/16.
 */
public class InputReader {

    private static final String DELIMINATOR = ",";

    List<List<Integer>> readInput(String fileName) {
        File file = new File(fileName);
        List<List<Integer>> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Integer> equation = convertToInt(Arrays.asList(line.split(DELIMINATOR)));
                input.add(equation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    List<Integer> convertToInt(List<String> strings) {
        return strings.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

}
