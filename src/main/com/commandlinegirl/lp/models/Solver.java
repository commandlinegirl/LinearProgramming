package com.commandlinegirl.lp.models;

import com.commandlinegirl.lp.Objective;
import com.commandlinegirl.lp.maths.SimpleMatrix;

public interface Solver {

    Objective solve(Objective objective, SimpleMatrix equations);
}
