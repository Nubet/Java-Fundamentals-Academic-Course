package io;

import domain.Vector;
import domain.VectorCalculator;
import exceptions.FileReadException;
import exceptions.InvalidVectorFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileInput
{
    private final String filePath;
    private final VectorCalculator calculator;

    public FileInput(String filePath) {
        this.filePath = filePath;
        this.calculator = new VectorCalculator();
    }

    public Vector[] readVectors() throws FileReadException {
        List<Vector> vectors = new ArrayList<>();
        Path path = Paths.get(filePath);

        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        }
        catch (IOException e) {
            throw new FileReadException("Error occurred while reading file: " + e.getMessage(), filePath, e);
        }

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            int lineNumber = i + 1;

            if (line.isEmpty()) {
                System.err.printf("Line %d is empty and will be skipped%n", lineNumber);
                continue;
            }

            try {
                vectors.add(calculator.parseVector(line));
            }
            catch (InvalidVectorFormatException e) {
                System.err.printf("Line %d has invalid format: %s%n", lineNumber, e.getInvalidInput());
            }
        }

        if (vectors.size() < 2) {
            throw new FileReadException(
                "File must contain at least two valid vectors, found: " + vectors.size(),
                filePath
            );
        }

        return vectors.toArray(new Vector[0]);
    }
}
