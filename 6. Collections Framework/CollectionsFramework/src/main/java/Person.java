import java.util.Objects;

public final class Person extends TelephoneEntry
{
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName, Address address) {
        super(address);
        this.firstName = StringValidator.validateNotEmpty(firstName, "First name");
        this.lastName = StringValidator.validateNotEmpty(lastName, "Last name");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String description() {
        return firstName + " " + lastName + " - " + address.city() + ", " + address.country();
    }

}
