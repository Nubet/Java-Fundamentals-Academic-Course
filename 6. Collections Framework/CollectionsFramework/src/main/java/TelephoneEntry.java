import java.util.Objects;

public abstract class TelephoneEntry {
    protected final Address address;

    protected TelephoneEntry(Address address) {
        this.address = Objects.requireNonNull(address, "Address cannot be null");
    }

    public final Address getAddress() {
        return address;
    }

    public final TelephoneNumber getTelephoneNumber() {
        return address.telephoneNumber();
    }

    public abstract String description();
}
