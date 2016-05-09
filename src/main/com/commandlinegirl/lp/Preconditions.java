package com.commandlinegirl.lp;

import java.util.List;

public class Preconditions {

    private Preconditions() {};

    public static boolean checkIndexNotWithinArray(List<List<Integer>> tableau, int columnIndex) {
        return tableau.stream().filter(e -> e.size() <= columnIndex).findAny().isPresent();
    }

}
