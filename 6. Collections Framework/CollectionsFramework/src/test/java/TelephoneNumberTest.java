import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TelephoneNumberTest
{
    @Test
    void shouldReturnNegativeWhenCountryCodeIsShorter() {
        TelephoneNumber shorter = new TelephoneNumber("1", "1234567890");
        TelephoneNumber longer = new TelephoneNumber("48", "123456789");

        assertTrue(shorter.compareTo(longer) < 0);
    }

    @Test
    void shouldReturnPositiveWhenCountryCodeIsLonger() {
        TelephoneNumber longer = new TelephoneNumber("48", "123456789");
        TelephoneNumber shorter = new TelephoneNumber("1", "1234567890");

        assertTrue(longer.compareTo(shorter) > 0);
    }

    @Test
    void shouldReturnZeroWhenBothFieldsAreEqual() {
        TelephoneNumber number1 = new TelephoneNumber("48", "664074784");
        TelephoneNumber number2 = new TelephoneNumber("48", "664074784");

        assertEquals(0, number1.compareTo(number2));
    }

    @Test
    void shouldCompareCountryCodesLexicographicallyWhenLengthsMatch() {
        TelephoneNumber number1 = new TelephoneNumber("44", "1234567890");
        TelephoneNumber number2 = new TelephoneNumber("48", "1234567890");

        assertTrue(number1.compareTo(number2) < 0);
        assertTrue(number2.compareTo(number1) > 0);
    }

    @Test
    void shouldThrowExceptionWhenComparingToNull() {
        TelephoneNumber number = new TelephoneNumber("48", "664074784");

        assertThrows(NullPointerException.class, () -> number.compareTo(null));
    }
}
