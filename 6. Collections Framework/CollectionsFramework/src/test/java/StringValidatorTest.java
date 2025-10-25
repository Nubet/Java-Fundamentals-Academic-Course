import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StringValidatorTest
{
    @Test
    void shouldReturnValueWhenValid() {
        String validValue = "Test String";
        String result = StringValidator.validateNotEmpty(validValue, "Test Field");

        assertEquals(validValue, result);
    }

    @Test
    void shouldThrowExceptionWhenNull() {
        assertThrows(NullPointerException.class,
            () -> StringValidator.validateNotEmpty(null, "Test Field"));
    }

    @Test
    void shouldThrowExceptionWhenEmpty() {
        assertThrows(IllegalArgumentException.class,
            () -> StringValidator.validateNotEmpty("", "Test Field"));
    }

    @Test
    void shouldThrowExceptionWhenOnlyWhitespace() {
        assertThrows(IllegalArgumentException.class,
            () -> StringValidator.validateNotEmpty("   ", "Test Field"));
    }
}
