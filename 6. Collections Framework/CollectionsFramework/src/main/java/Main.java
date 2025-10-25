import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public final class Main
{
    private Main() {
        throw new AssertionError("Main class - cannot be instantiated");
    }

    public static void main(String[] args) {
        Map<TelephoneNumber, TelephoneEntry> telephoneDirectory = createTelephoneDirectory();
        displayDirectory(telephoneDirectory);
    }

    private static Map<TelephoneNumber, TelephoneEntry> createTelephoneDirectory() {
        Map<TelephoneNumber, TelephoneEntry> directory = new TreeMap<>();

        addPersonEntry(directory, "48", "664074784",
            "ul. Karolinki 146", "Ruda Śląska", "41-707", "Polska",
            "Sławomir", "Pawłowski");

        addPersonEntry(directory, "48", "987654321",
            "ul. Dąbrowskiego 107", "Poznań", "60-925", "Polska",
            "Tomasz", "Adamczyk");


        addCompanyEntry(directory, "1", "8265502806",
            "Madison Avenue 550", "New York", "10022", "USA",
            "Prosacco Inc");

        addCompanyEntry(directory, "49", "9876543210",
            "Kaiserdamm 42", "Berlin", "14057", "Germany",
            "Hirthe and Sons");

        return directory;
    }

    private static void addPersonEntry(Map<TelephoneNumber, TelephoneEntry> directory,
        String countryCode, String localNumber,
        String street, String city, String postalCode, String country,
        String firstName, String lastName) {
        TelephoneNumber number = new TelephoneNumber(countryCode, localNumber);
        Address address = new Address(street, city, postalCode, country, number);
        Person person = new Person(firstName, lastName, address);
        directory.put(number, person);
    }

    private static void addCompanyEntry(Map<TelephoneNumber, TelephoneEntry> directory,
        String countryCode, String localNumber,
        String street, String city, String postalCode, String country,
        String companyName) {
        TelephoneNumber number = new TelephoneNumber(countryCode, localNumber);
        Address address = new Address(street, city, postalCode, country, number);
        Company company = new Company(companyName, address);
        directory.put(number, company);
    }

    private static void displayDirectory(Map<TelephoneNumber, TelephoneEntry> directory) {
        System.out.println("\n\tTELEPHONE DIRECTORY");

        Iterator<Map.Entry<TelephoneNumber, TelephoneEntry>> iterator = directory.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<TelephoneNumber, TelephoneEntry> entry = iterator.next();
            System.out.println("Phone: " + entry.getKey());
            System.out.println(entry.getValue().description());
            System.out.println();
        }
    }
}
