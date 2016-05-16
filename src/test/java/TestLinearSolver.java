import com.commandlinegirl.lp.InputReader;
import com.commandlinegirl.lp.Objective;
import com.commandlinegirl.lp.OptimizationType;
import com.commandlinegirl.lp.Tableau;
import com.commandlinegirl.lp.exceptions.InfeasibleException;
import com.commandlinegirl.lp.exceptions.UnboundedException;
import com.commandlinegirl.lp.solvers.LinearSolver;
import com.commandlinegirl.lp.solvers.Solver;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

public class TestLinearSolver {

    @Test
    public void testSolve() throws UnboundedException, InfeasibleException {
        InputReader freader = new InputReader();
        List<List<Double>> input = freader.readInput("/Users/kereish/repos/LinearProgramming/src/test/resources/input01.txt");

        double[][] converted = freader.convertDoublesMatrix(input);
        Tableau tableau = new Tableau(converted);

        Solver solver = new LinearSolver(OptimizationType.MAX);
        Objective result = solver.solve(tableau);
        double[] expected = new double[] {0.0, 0.0, 0.19999999999999996, 2.6, 0.8, 0.0, 1.0, 708.0};
        Assert.assertEquals(expected, result.getSolution());

    }
}
