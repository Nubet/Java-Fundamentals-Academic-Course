import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static java.util.Arrays.asList;

class QuadraticEquationUtilsTest {

    @Test
    void testComputeDelta() {
        assertEquals(1, QuadraticEquationUtils.computeDelta(1, 3, 2));
        assertEquals(0, QuadraticEquationUtils.computeDelta(1, 2, 1));
        assertEquals(-7, QuadraticEquationUtils.computeDelta(1, 1, 2));
        assertEquals(100, QuadraticEquationUtils.computeDelta(1, 10, 0));
        assertEquals(1, QuadraticEquationUtils.computeDelta(-1, -3, -2));
        assertEquals(-16, QuadraticEquationUtils.computeDelta(1, 0, 4));
        assertEquals(25, QuadraticEquationUtils.computeDelta(1, 5, 0));
    }

    @Test
    void testHasSolutions() {
        assertTrue(QuadraticEquationUtils.hasSolutions(1));
        assertTrue(QuadraticEquationUtils.hasSolutions(0));
        assertFalse(QuadraticEquationUtils.hasSolutions(-1));
    }

    @Test
    void testSolve_OneSolution() {
        List<Double> solutions = QuadraticEquationUtils.solve(1, 2, 1);
        assertEquals(1, solutions.size());
        assertEquals(-1.0, solutions.get(0));
    }

    @Test
    void testSolve_TwoSolutions() {
        List<Double> solutions = QuadraticEquationUtils.solve(1, -5, 6);
        assertEquals(2, solutions.size());
        assertTrue(solutions.containsAll(asList(2.0, 3.0)));
    }

    @Test
    void testSolve_NoSolutions() {
        List<Double> solutions = QuadraticEquationUtils.solve(1, 1, 1);
        assertNull(solutions);
    }

    @Test
    void testSolve_ThrowsExceptionWhenAIsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuadraticEquationUtils.solve(0, 1, 1);
        });
    }
}