package com.commandlinegirl.lp.solvers;

import com.commandlinegirl.lp.Objective;
import com.commandlinegirl.lp.Tableaux;

public interface Solver {

    Objective solve(Tableaux equations); //throws InfeasibleException, UnboundedException;
}
