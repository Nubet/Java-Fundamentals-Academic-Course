package exceptions;

import domain.Vector;

public class DifferentVectorsLengthsException extends Exception
{
    private final Vector[] vectors;

    public Vector[] getVectors() {
        return vectors;
    }

    public DifferentVectorsLengthsException(String message, Vector[] vectors) {
        super(message);
        this.vectors = vectors;
    }
}
