import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            //javac Main.java
            System.out.println("example usage: 'java Main <a: coefficient> <b: coefficient> <c: constant>' ");
            return;
        }

        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = Integer.parseInt(args[2]);


            try {
                List<Double> solutions = QuadraticEquationUtils.solve(a, b, c);

                if (null == solutions)
                    System.out.println("The equation has no real solutions.");
                else if (solutions.size() == 1)
                    System.out.println("The equation has one solution: x = " + solutions.get(0));
                else
                    System.out.println("The equation has two solutions: x1 = " + solutions.get(0) + ", x2 = " + solutions.get(1));

            } catch (IllegalArgumentException e) {
                System.out.println("Error occured: " + e.getMessage());
                System.out.println("The coefficient 'a' cannot be zero in quadratic equation");
            }
        } catch (NumberFormatException e) {
            System.out.println("All of the coefficients must be integer type");
        }
    }
}
