package io;

import domain.Vector;
import domain.VectorCalculator;
import exceptions.InvalidVectorFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput
{
    private final VectorCalculator calculator;
    private final int vectorCount;

    public ConsoleInput(int vectorCount) {
        this.calculator = new VectorCalculator();
        this.vectorCount = vectorCount;
    }

    public Vector[] readVectors() throws InvalidVectorFormatException {
        Scanner scanner = new Scanner(System.in);
        List<Vector> vectors = new ArrayList<>();

        System.out.println();
        for (int i = 1; i <= vectorCount; i++) {
            Vector vector = readSingleVector(scanner, "Enter vector #" + i + ": ");
            vectors.add(vector);
        }
        System.out.println();

        return vectors.toArray(new Vector[0]);
    }

    private Vector readSingleVector(Scanner scanner, String prompt) throws InvalidVectorFormatException {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                Vector vector = calculator.parseVector(input);
                System.out.println("  -> Parsed as: " + vector);
                return vector;
            }
            catch (InvalidVectorFormatException e) {
                System.out.println("  -> Error: " + e.getMessage());
                System.out.print("  -> Please try again: \n");
            }
        }
    }
}
