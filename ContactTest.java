package contacts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    private Contact valid() {
        return new Contact("ID123", "John", "Doe", "1234567890", "123 Main St");
    }

    @Test
    void createsValidContact() {
        Contact c = valid();
        assertEquals("ID123", c.getContactId());
        assertEquals("John",  c.getFirstName());
        assertEquals("Doe",   c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    @Test
    void contactIdCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "John", "Doe", "1234567890", "A"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ABCDEFGHIJK", "John", "Doe", "1234567890", "A"));
    }

    @Test
    void firstNameRequiredAndMax10() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", null, "Doe", "1234567890", "A"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "ABCDEFGHIJK", "Doe", "1234567890", "A"));
    }

    @Test
    void lastNameRequiredAndMax10() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "John", null, "1234567890", "A"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "John", "ABCDEFGHIJK", "1234567890", "A"));
    }

    @Test
    void phoneExactly10Digits() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "John", "Doe", null, "A"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "John", "Doe", "12345", "A"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "John", "Doe", "12345678901", "A"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "John", "Doe", "12345abcde", "A"));
    }

    @Test
    void addressRequiredAndMax30() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "John", "Doe", "1234567890", null));
        String over30 = "1234567890123456789012345678901";
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "John", "Doe", "1234567890", over30));
    }

    @Test
    void updateFieldsValidates() {
        Contact c = valid();
        c.setFirstName("Jane");
        c.setLastName("Smith");
        c.setPhone("0987654321");
        c.setAddress("456 Oak Ave");
        assertEquals("Jane", c.getFirstName());
        assertEquals("Smith", c.getLastName());
        assertEquals("0987654321", c.getPhone());
        assertEquals("456 Oak Ave", c.getAddress());

        assertThrows(IllegalArgumentException.class, () -> c.setFirstName("ABCDEFGHIJK"));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("1234"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
    }
}
