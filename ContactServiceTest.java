package contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setUp() { service = new ContactService(); }

    private Contact c1() { return new Contact("ID1", "John", "Doe", "1234567890", "Addr 1"); }
    private Contact c2() { return new Contact("ID2", "Anna", "Lee", "0987654321", "Addr 2"); }

    @Test
    void addContactStoresByUniqueId() {
        service.addContact(c1());
        assertTrue(service.get("ID1").isPresent());
    }

    @Test
    void addingDuplicateIdFails() {
        service.addContact(c1());
        assertThrows(IllegalArgumentException.class, () -> service.addContact(c1()));
    }

    @Test
    void deleteContactRemovesIt() {
        service.addContact(c1());
        service.deleteContact("ID1");
        assertTrue(service.get("ID1").isEmpty());
    }

    @Test
    void deleteUnknownIdThrows() {
        assertThrows(NoSuchElementException.class, () -> service.deleteContact("NOPE"));
    }

    @Test
    void updateFieldsById() {
        service.addContact(c2());
        service.updateFirstName("ID2", "Ann");
        service.updateLastName("ID2", "Li");
        service.updatePhone("ID2", "1112223333");
        service.updateAddress("ID2", "New Address");
        Contact updated = service.get("ID2").orElseThrow();
        assertEquals("Ann", updated.getFirstName());
        assertEquals("Li", updated.getLastName());
        assertEquals("1112223333", updated.getPhone());
        assertEquals("New Address", updated.getAddress());
    }

    @Test
    void updateValidatesInputs() {
        service.addContact(c1());
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("ID1", null));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("ID1", "12"));
        assertThrows(NoSuchElementException.class, () -> service.updateAddress("MISSING", "X"));
    }
}
