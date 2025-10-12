import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParenthesesValidatorTest
{
    @Test
    public void testEmptyString() {
        assertTrue(ParenthesesValidator.areParenthesesMatched(""));
    }

    @Test
    public void testSimpleMatchedParentheses() {
        assertTrue(ParenthesesValidator.areParenthesesMatched("()"));
    }

    @Test
    public void testNestedMatchedParentheses() {
        assertTrue(ParenthesesValidator.areParenthesesMatched("(())"));
    }

    @Test
    public void testUnmatchedParentheses() {
        assertFalse(ParenthesesValidator.areParenthesesMatched("(()"));
    }

    @Test
    public void testUnmatchedParenthesesWithClosingFirst() { assertFalse(ParenthesesValidator.areParenthesesMatched(")(")); }

    @Test
    public void testMixedCharactersMatched() {
        assertTrue(ParenthesesValidator.areParenthesesMatched("(a+b)"));
    }

    @Test
    public void testMixedCharactersUnmatched() {
        assertFalse(ParenthesesValidator.areParenthesesMatched("(a+b))"));
    }

    @Test
    public void testNoParentheses() {
        assertTrue(ParenthesesValidator.areParenthesesMatched("abc"));
    }
}
