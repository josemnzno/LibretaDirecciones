import java.util.*;

public class AddressBook {
    private static AddressBook instance = new AddressBook();
    private List<AddressEntry> entries;

    private AddressBook() {
        entries = new ArrayList<>();
    }

    public static AddressBook getInstance() {
        return instance;
    }

    public void addEntry(AddressEntry entry) {
        entries.add(entry);
    }

    public void deleteEntry(AddressEntry entry) {
        entries.remove(entry);
    }

    public List<AddressEntry> searchByLastName(String lastNamePrefix) {
        List<AddressEntry> matchingEntries = new ArrayList<>();

        for (AddressEntry entry : entries) {
            if (entry.getLastName().startsWith(lastNamePrefix)) {
                matchingEntries.add(entry);
            }
        }

        return matchingEntries;
    }

    public List<AddressEntry> getEntriesOrderedByLastName() {
        List<AddressEntry> sortedEntries = new ArrayList<>(entries);
        sortedEntries.sort(Comparator.comparing(AddressEntry::getLastName));

        return sortedEntries;
    }
}
