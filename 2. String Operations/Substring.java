public final class Substring {
    private Substring() {}

    public static String extractSubstring(String s, int start1, int end1) {
        validateOneBasedExclusiveEnd(s, start1, end1);
        int start0 = start1 - 1;
        int end0 = end1; // exclusive
        return s.substring(start0, end0);
    }

    public static String removeSubstring(String s, int start1, int end1) {
        validateOneBasedExclusiveEnd(s, start1, end1);
        int start0 = start1 - 1;
        int end0 = end1; // exclusive
        return s.substring(0, start0) + s.substring(end0);
    }

    private static void validateOneBasedExclusiveEnd(String s, int start1, int end1) {
        if (start1 <= 0 || end1 <= 0) {
            throw new IllegalArgumentException("Indices must be >= 1");
        }
        int len = s.length();
        if (start1 >= end1) {
            throw new IllegalArgumentException("start1 must be < than end1");
        }
        if (start1 >= len) {
            throw new IllegalArgumentException("start1 must be <= string length (" + len + ")");
        }
        if (end1 >= len) {
            throw new IllegalArgumentException("end1 must be <= string length (" + len + ")");
        }
    }
}