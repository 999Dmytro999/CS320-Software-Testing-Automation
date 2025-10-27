package contacts;

import java.util.Objects;

public class Contact {

    private static final int ID_MAX = 10;
    private static final int NAME_MAX = 10;
    private static final int ADDRESS_MAX = 30;
    private static final String PHONE_REGEX = "\\d{10}";

    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName,
                   String phone, String address) {
        this.contactId = validateId(contactId);
        this.firstName = validateName(firstName, "firstName");
        this.lastName  = validateName(lastName,  "lastName");
        this.phone     = validatePhone(phone);
        this.address   = validateAddress(address);
    }

    public String getContactId() { return contactId; }
    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public String getPhone()     { return phone; }
    public String getAddress()   { return address; }

    public void setFirstName(String firstName) {
        this.firstName = validateName(firstName, "firstName");
    }
    public void setLastName(String lastName) {
        this.lastName = validateName(lastName, "lastName");
    }
    public void setPhone(String phone) {
        this.phone = validatePhone(phone);
    }
    public void setAddress(String address) {
        this.address = validateAddress(address);
    }

    private static String validateId(String id) {
        if (id == null || id.length() == 0 || id.length() > ID_MAX)
            throw new IllegalArgumentException("contactId must be 1-10 chars");
        return id;
    }
    private static String validateName(String value, String field) {
        if (value == null || value.length() == 0 || value.length() > NAME_MAX)
            throw new IllegalArgumentException(field + " must be 1-10 chars");
        return value;
    }
    private static String validatePhone(String value) {
        if (value == null || !value.matches(PHONE_REGEX))
            throw new IllegalArgumentException("phone must be exactly 10 digits");
        return value;
    }
    private static String validateAddress(String value) {
        if (value == null || value.length() == 0 || value.length() > ADDRESS_MAX)
            throw new IllegalArgumentException("address must be 1-30 chars");
        return value;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact c = (Contact) o;
        return Objects.equals(contactId, c.contactId);
    }
    @Override public int hashCode() { return Objects.hash(contactId); }
}