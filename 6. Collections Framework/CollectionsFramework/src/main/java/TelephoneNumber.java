import java.util.Objects;

public record TelephoneNumber(String countryCode, String localNumber)
    implements Comparable<TelephoneNumber> {

    public TelephoneNumber {
        countryCode = validatePhoneNumber(countryCode, "Country code");
        localNumber = validatePhoneNumber(localNumber, "Local number");
    }

    private static String validatePhoneNumber(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " cannot be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        if (!value.matches("\\d+")) {
            throw new IllegalArgumentException(fieldName + " must contain only digits");
        }
        return value;
    }

    @Override
    public int compareTo(TelephoneNumber other) {
        Objects.requireNonNull(other, "Cannot compare to null TelephoneNumber");

        // compare by country code length first (so "1" < "48")
        if (countryCode.length() != other.countryCode.length())
            return countryCode.length() - other.countryCode.length();

        int cc = countryCode.compareTo(other.countryCode);
        if (cc != 0) return cc;

        // then by local number length and value
        if (localNumber.length() != other.localNumber.length())
            return localNumber.length() - other.localNumber.length();

        return localNumber.compareTo(other.localNumber);
    }

    @Override
    public String toString() {
        return "+" + countryCode + " " + localNumber;
    }
}
