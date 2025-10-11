import java.util.Random;
import java.util.Scanner;

public class Main {
    static int MAX = 100;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int tries = 0;
            Random random = new Random();
            int n = random.nextInt(MAX + 1);
            int guess;

            System.out.println("Welcome in the guessing game!");
            System.out.printf("Please Enter a Number from [0,%d] \n", MAX);

            while (true) {
                System.out.print("guess: ");
                guess = scanner.nextInt();
                tries++;
                if(guess == n){
                    System.out.println("CONGRATS! YOU WON!!!");
                    System.out.printf("You needed %d tries to win!", tries);
                    System.out.println("do you want to play again? [enter y/n]");
                    System.out.print("choice: ");
                    scanner.nextLine(); // consuming newline character
                    String choice = scanner.nextLine().toLowerCase();

                    if(choice.equals("n")){
                        System.out.println("EXITING!");
                        scanner.close();
                        return;
                    }
                    else {
                        tries = 0;
                        n = random.nextInt(MAX + 1);
                        continue;
                    }
                }
                if(guess > n)
                    System.out.println("TOO HIGH!");
                else if(guess < n)
                    System.out.println("TOO LOW!");
            }
        }
    }
}
