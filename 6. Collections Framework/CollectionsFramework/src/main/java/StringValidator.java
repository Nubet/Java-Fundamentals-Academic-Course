import java.util.Objects;

final class StringValidator {

    private StringValidator() {
        throw new AssertionError("Utility class - cannot be instantiated");
    }

    static String validateNotEmpty(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " cannot be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return value;
    }
}

