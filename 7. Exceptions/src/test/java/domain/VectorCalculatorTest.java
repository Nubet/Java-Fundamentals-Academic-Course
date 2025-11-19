package domain;

import exceptions.DifferentVectorsLengthsException;
import exceptions.InvalidVectorFormatException;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class VectorCalculatorTest {
    private final VectorCalculator calculator = new VectorCalculator();

    @Test
    void shouldAddTwoVectorsOfSameLength() throws DifferentVectorsLengthsException {
        Vector v1 = new Vector(Arrays.asList(1, 2));
        Vector v2 = new Vector(Arrays.asList(3, 4));

        Vector result = calculator.addAll(new Vector[]{v1, v2});
        assertEquals("(4,6)", result.toString());
    }

    @Test
    void shouldAddThreeVectorsOfSameLength() throws DifferentVectorsLengthsException {
        Vector v1 = new Vector(Arrays.asList(1, 2, 3));
        Vector v2 = new Vector(Arrays.asList(4, 5, 6));
        Vector v3 = new Vector(Arrays.asList(7, 8, 9));

        Vector result = calculator.addAll(new Vector[]{v1, v2, v3});
        assertEquals("(12,15,18)", result.toString());
    }

    @Test
    void shouldAddFourVectorsOfSameLength() throws DifferentVectorsLengthsException {
        Vector v1 = new Vector(Arrays.asList(1, 1));
        Vector v2 = new Vector(Arrays.asList(2, 2));
        Vector v3 = new Vector(Arrays.asList(3, 3));
        Vector v4 = new Vector(Arrays.asList(4, 4));

        Vector result = calculator.addAll(new Vector[]{v1, v2, v3, v4});
        assertEquals("(10,10)", result.toString());
    }

    @Test
    void shouldThrowExceptionWhenVectorsHaveDifferentLengths() {
        Vector v1 = new Vector(Arrays.asList(1, 2));
        Vector v2 = new Vector(Arrays.asList(3, 4, 5));

        DifferentVectorsLengthsException exception = assertThrows(
            DifferentVectorsLengthsException.class,
            () -> calculator.addAll(new Vector[]{v1, v2})
        );
        assertEquals("Cannot add vectors with different lengths", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLessThanTwoVectorsProvided() {
        Vector v1 = new Vector(Arrays.asList(1, 2));

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.addAll(new Vector[]{v1})
        );
        assertEquals("At least two vectors are required for addition", exception.getMessage());
    }

    @Test
    void shouldParseValidVectorInput() throws InvalidVectorFormatException {
        String input = "1,2,3";
        Vector result = calculator.parseVector(input);
        assertEquals("(1,2,3)", result.toString());
    }

    @Test
    void shouldIgnoreNonNumericElements() throws InvalidVectorFormatException {
        String input = "1,a,2,@,3";
        Vector result = calculator.parseVector(input);
        assertEquals("(1,2,3)", result.toString());
    }

    @Test
    void shouldThrowExceptionForEmptyInput() {
        String input = "";
        InvalidVectorFormatException exception = assertThrows(
            InvalidVectorFormatException.class,
            () -> calculator.parseVector(input)
        );
        assertEquals("Input cannot be empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForOnlyInvalidNumbers() {
        String input = "a,b,c";
        InvalidVectorFormatException exception = assertThrows(
            InvalidVectorFormatException.class,
            () -> calculator.parseVector(input)
        );
        assertEquals("No valid numbers found in input", exception.getMessage());
    }
}
