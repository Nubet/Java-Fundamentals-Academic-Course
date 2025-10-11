import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubstringTest {

    @Test
    void testExtractSubstringCorrect() {
        assertEquals("elc", Substring.extractSubstring("Welcome", 2, 4));
    }

    @Test
    void testRemoveSubstringCorrect() {
        assertEquals("Wome", Substring.removeSubstring("Welcome", 2, 4));
    }

    @Test
    void testStartNotNaturalNumber() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> Substring.extractSubstring("abc", 0, 2));
        assertEquals("Indices must be >= 1", ex.getMessage());
    }

    @Test
    void testStartGreaterOrEqualEnd() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> Substring.extractSubstring("abc", 2, 2));
        assertEquals("start1 must be < than end1", ex.getMessage());
    }

    @Test
    void testEndIndexTooBig() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> Substring.extractSubstring("abc", 1, 5));
        assertEquals("end1 must be <= string length (3)", ex.getMessage());
    }

    @Test
    void testStartIndexTooBig() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> Substring.removeSubstring("abc", 4, 5));
        assertEquals("start1 must be <= string length (3)", ex.getMessage());
    }
}