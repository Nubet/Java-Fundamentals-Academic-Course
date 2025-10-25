import java.util.Objects;


public record Address(
    String street,
    String city,
    String postalCode,
    String country,
    TelephoneNumber telephoneNumber
) {
    public Address {
        street = StringValidator.validateNotEmpty(street, "Street");
        city = StringValidator.validateNotEmpty(city, "City");
        postalCode = StringValidator.validateNotEmpty(postalCode, "Postal code");
        country = StringValidator.validateNotEmpty(country, "Country");
        Objects.requireNonNull(telephoneNumber, "Telephone number cannot be null");
    }
}
