package exceptions;

public class InvalidVectorFormatException extends Exception
{
    private final String invalidInput;

    public String getInvalidInput() {
        return invalidInput;
    }

    public InvalidVectorFormatException(String message, String invalidInput) {
        super(message);
        this.invalidInput = invalidInput;
    }
}
