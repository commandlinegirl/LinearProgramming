
import com.commandlinegirl.lp.InputReader;
import com.commandlinegirl.lp.Objective;
import com.commandlinegirl.lp.OptimizationType;
import com.commandlinegirl.lp.Tableau;
import com.commandlinegirl.lp.exceptions.InfeasibleException;
import com.commandlinegirl.lp.exceptions.UnboundedException;
import com.commandlinegirl.lp.solvers.LinearSolver;
import com.commandlinegirl.lp.solvers.Solver;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.List;

public class TestLinearSolver {

    @Test
    public void testSolve() throws UnboundedException, InfeasibleException {
        InputReader freader = new InputReader();

        URL url = Thread.currentThread().getContextClassLoader().getResource("input01.txt");
        Assert.assertNotNull(url);

        List<List<Double>> input = freader.readInput(url.getPath());

        double[][] converted = freader.convertDoublesMatrix(input);
        Tableau tableau = new Tableau(converted);

        Solver solver = new LinearSolver(OptimizationType.MAX);
        Objective result = solver.solve(tableau);
        double[] expected = new double[] {708.0, 48.0, 84.0, 0.0, 0.0, 0.0, 60.0, 0.0};
        Assert.assertArrayEquals(expected, result.getSolution(), Tableau.EPSILON);

    }
}
