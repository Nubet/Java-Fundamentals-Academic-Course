import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public final class QuadraticEquationUtils {
    private QuadraticEquationUtils(){};

    public static int computeDelta(int a, int b, int c){
        return (b*b)-4*a*c;
    }
    public static boolean hasSolutions(int delta){
        return delta >= 0;
    }
    public static List<Double> solve(int a, int b, int c){
        if(a == 0){
            throw new IllegalArgumentException("a must differ from 0");
        }
        int delta = computeDelta(a,b,c);
        if(!hasSolutions(delta))
            return null;

        ArrayList<Double> solutions = new ArrayList<>();
        if(delta == 0){
            double x0 = -b / (2.0 * a);
            solutions.add(x0);
        }
        if(delta > 0){
            double x1 = (-b - sqrt(delta)) / (2.0 * a);
            double x2 = (-b + sqrt(delta)) / (2.0 * a);
            solutions.add(x1);
            solutions.add(x2);
        }
        return solutions;
    }

}
