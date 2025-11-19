package domain;

import exceptions.DifferentVectorsLengthsException;
import exceptions.InvalidVectorFormatException;

import java.util.ArrayList;
import java.util.List;

public class VectorCalculator
{
    public Vector parseVector(String input) throws InvalidVectorFormatException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidVectorFormatException("Input cannot be empty", input);
        }

        String[] vectorParts = input.split(",");
        List<Integer> vectorComponents = new ArrayList<>();
        List<String> ignoredElements = new ArrayList<>();

        for (String part : vectorParts) {
            try {
                String trimmedPart = part.trim();
                if (!trimmedPart.isEmpty())
                    vectorComponents.add(Integer.parseInt(trimmedPart));
            }
            catch (NumberFormatException e) {
                ignoredElements.add(part.trim());
            }
        }

        if (vectorComponents.isEmpty())
            throw new InvalidVectorFormatException("No valid numbers found in input", input);

        if (!ignoredElements.isEmpty()) {
            System.out.print("  -> Ignored: ");
            for (int i = 0; i < ignoredElements.size(); i++) {
                System.out.printf("'%s'", ignoredElements.get(i));
                if (i < ignoredElements.size() - 1)
                    System.out.print(", ");
            }
            System.out.println();
        }

        return new Vector(vectorComponents);
    }

    public Vector addAll(Vector[] vectors) throws DifferentVectorsLengthsException {
        if (vectors == null || vectors.length < 2) {
            throw new IllegalArgumentException("At least two vectors are required for addition");
        }

        int expectedLength = vectors[0].getLength();
        for (Vector v : vectors) {
            if (v.getLength() != expectedLength) {
                throw new DifferentVectorsLengthsException(
                    "Cannot add vectors with different lengths", vectors
                );
            }
        }

        List<Integer> resultComponents = new ArrayList<>();
        for (int i = 0; i < expectedLength; i++) {
            int sum = 0;
            for (Vector v : vectors) {
                sum += v.getComponent(i);
            }
            resultComponents.add(sum);
        }

        return new Vector(resultComponents);
    }
}
