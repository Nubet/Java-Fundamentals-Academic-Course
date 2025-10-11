import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("example usage: java Substring <a: text> <b: start_index> <c: end_index>");
            return;
        }

        try {
            String inputString = args[0];
            int startIndex = Integer.parseInt(args[1]);
            int endIndex = Integer.parseInt(args[2]);

            String extracted = Substring.extractSubstring(inputString, startIndex, endIndex);
            String remaining = Substring.removeSubstring(inputString, startIndex, endIndex);

            System.out.println(extracted);
            System.out.println(remaining);
        } catch (NumberFormatException e) {
            System.out.println("Start and end indices must be integers");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
