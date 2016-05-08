package com.commandlinegirl.lp;

import java.util.List;

/**
 * Created on 05/05/16.
 */
public class Preconditions {

    private Preconditions() {};

    public static boolean checkIndexNotWithinArray(List<List<Integer>> tableau, int columnIndex) {
        return tableau.stream().filter(e -> e.size() <= columnIndex).findAny().isPresent();
    }

}
