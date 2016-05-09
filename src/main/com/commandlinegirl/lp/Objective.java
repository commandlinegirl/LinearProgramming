package com.commandlinegirl.lp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Objective {

    private List<Double> vector = new ArrayList<>();

    public Objective(List<Double> vector) {
        this.vector = vector;
    }

    public List<Double> getVector() {
        return Collections.unmodifiableList(vector);
    }

    /** Return the index of the lowest value in a list **/
    public int getMinIndex() {
        int minIndex = 0; //TODO: vector might be null!
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) < vector.get(minIndex))
                minIndex = i;
        }
        return minIndex;
    }
}
