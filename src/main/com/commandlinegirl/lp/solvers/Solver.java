package com.commandlinegirl.lp.solvers;

import com.commandlinegirl.lp.Objective;
import com.commandlinegirl.lp.Tableau;
import com.commandlinegirl.lp.exceptions.InfeasibleException;
import com.commandlinegirl.lp.exceptions.UnboundedException;

public interface Solver {

    Objective solve(Tableau equations) throws InfeasibleException, UnboundedException;

    boolean validate(Tableau tableau);
}
