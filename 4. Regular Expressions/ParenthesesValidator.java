import java.util.Scanner;

public class ParenthesesValidator
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an expression:");
        String ex = scanner.nextLine();

        if (areParenthesesMatched(ex))
            System.out.println("parentheses matched");
        else
            System.out.println("parentheses not matched");

        scanner.close();
    }

    public static boolean areParenthesesMatched(String ex) {
        String filteredExpression = ex.replaceAll("[^\\(\\)]", "");
        String lastStringState = "";

        while (!filteredExpression.equals(lastStringState)) {
            lastStringState = filteredExpression;
            filteredExpression = filteredExpression.replaceAll("\\(\\)", "");
        }

        return filteredExpression.isEmpty();
    }
}
