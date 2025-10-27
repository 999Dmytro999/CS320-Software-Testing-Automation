package contacts;

import java.util.*;

public class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        Objects.requireNonNull(contact, "contact");
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Duplicate contactId: " + id);
        }
        contacts.put(id, contact);
    }

    public void deleteContact(String contactId) {
        requireNonNullId(contactId);
        if (contacts.remove(contactId) == null) {
            throw new NoSuchElementException("No contact with id: " + contactId);
        }
    }

    public void updateFirstName(String contactId, String firstName) {
        require(contactId).setFirstName(firstName);
    }
    public void updateLastName(String contactId, String lastName) {
        require(contactId).setLastName(lastName);
    }
    public void updatePhone(String contactId, String phone) {
        require(contactId).setPhone(phone);
    }
    public void updateAddress(String contactId, String address) {
        require(contactId).setAddress(address);
    }

    public Optional<Contact> get(String contactId) {
        requireNonNullId(contactId);
        return Optional.ofNullable(contacts.get(contactId));
    }

    private Contact require(String id) {
        requireNonNullId(id);
        Contact c = contacts.get(id);
        if (c == null) throw new NoSuchElementException("No contact with id: " + id);
        return c;
    }

    private static void requireNonNullId(String id) {
        if (id == null) throw new IllegalArgumentException("id cannot be null");
    }
}
