public final class Company extends TelephoneEntry
{
    private final String name;

    public Company(String name, Address address) {
        super(address);
        this.name = StringValidator.validateNotEmpty(name, "Company name");
    }

    public String getName() {
        return name;
    }

    @Override
    public String description() {
        return name + " - " + address.city() + ", " + address.country();
    }

}
