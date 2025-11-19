package service;

import domain.Vector;
import domain.VectorCalculator;
import exceptions.DifferentVectorsLengthsException;

public class VectorCalculatorManager
{
    private final VectorCalculator vectorCalculator;

    public VectorCalculatorManager(VectorCalculator vectorCalculator) {
        this.vectorCalculator = vectorCalculator;
    }

    public void processVectors(Vector[] vectors) throws DifferentVectorsLengthsException {
        if (vectors.length < 2) {
            System.err.println("Not enough vectors to perform operations. At least 2 vectors are required.");
            return;
        }

        Vector sum = vectorCalculator.addAll(vectors);
        displayResult(vectors, sum);
    }

    public void handleDifferentLengths(DifferentVectorsLengthsException e) {
        Vector[] vectors = e.getVectors();
        if (vectors == null || vectors.length < 2) return;

        System.out.println("Vectors have different lengths!");

        for (int i = 1; i < vectors.length; i++) {
            String status = vectors[i].getLength() > vectors[0].getLength() ? "bigger" : "lower";
            System.out.printf("%s the vector length is %s than %s vector length.\n",
                vectors[i], status, vectors[0]);
        }

        System.out.println("\nAll vectors must have the same length to perform addition.");
        System.out.println("Please re-enter the vectors with matching lengths.\n");
    }

    private void displayResult(Vector[] vectors, Vector sum) {
        System.out.println("Vector Addition Result:");

        for (int i = 0; i < vectors.length; i++) {
            System.out.print(vectors[i]);
            if (i < vectors.length - 1)
                System.out.print(" + ");
        }

        System.out.println(" = " + sum);
    }
}
