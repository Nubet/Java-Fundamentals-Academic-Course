package ui;

import domain.Vector;
import domain.VectorCalculator;
import io.ConsoleInput;
import io.FileInput;
import exceptions.DifferentVectorsLengthsException;
import exceptions.FileReadException;
import exceptions.InvalidVectorFormatException;
import service.VectorCalculatorManager;

import java.util.Scanner;

public class ConsoleRunner
{
    private static final String DEFAULT_FILE_PATH = "/src/main/resources/input/valid_vectors.txt";
    private static final VectorCalculator calculator = new VectorCalculator();
    private static final VectorCalculatorManager manager = new VectorCalculatorManager(calculator);

    public static void main(String[] args) {
        runMenuLoop();
    }

    public static void runMenuLoop() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            displayMenu();
            System.out.print("> ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    processConsoleInput();
                    waitForUserInput();
                    break;
                case "2":
                    processFileInput();
                    waitForUserInput();
                    break;
                case "3":
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Choose from 1-3.");
                    break;
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n\tVector Calculator");
        System.out.println("1. Enter vectors via console");
        System.out.println("2. Read vectors from file");
        System.out.println("3. Exit");
    }

    private static void processConsoleInput() {
        int vectorCount = getVectorCountFromUser();

        boolean retry = true;
        while (retry) {
            try {
                Vector[] vectors = new ConsoleInput(vectorCount).readVectors();
                manager.processVectors(vectors);
                retry = false;
            }
            catch (DifferentVectorsLengthsException e) {
                manager.handleDifferentLengths(e);
                retry = true;
            }
            catch (InvalidVectorFormatException e) {
                System.err.println("\nError: " + e.getMessage());
                retry = true;
            }
        }
    }

    private static int getVectorCountFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        while (true) {
            System.out.print("How many vectors do you want to enter? ");
            String input = scanner.nextLine().trim();
            try {
                int count = Integer.parseInt(input);
                if (count < 2) {
                    System.out.println("  -> Please enter at least 2 vectors.");
                    continue;
                }
                return count;
            }
            catch (NumberFormatException e) {
                System.out.println("  -> Invalid number. Please try again.");
            }
        }
    }

    private static void processFileInput() {
        try {
            String filePath = getFilePathFromUser();
            Vector[] vectors = new FileInput(filePath).readVectors();
            displayVectors(vectors);
            manager.processVectors(vectors);
        }
        catch (FileReadException e) {
            System.err.println("Error: " + e.getMessage());
        }
        catch (DifferentVectorsLengthsException e) {
            manager.handleDifferentLengths(e);
        }
    }

    private static String getFilePathFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file path (or press Enter for default '" + DEFAULT_FILE_PATH + "'): ");
        String filePath = scanner.nextLine();
        return filePath.trim().isEmpty() ? DEFAULT_FILE_PATH : filePath;
    }

    private static void waitForUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void displayVectors(Vector[] vectors) {
        System.out.println("\n" + "===================================");
        System.out.println("Found " + vectors.length + " vectors in file:");
        for (int i = 0; i < vectors.length; i++) {
            System.out.println("  Vector " + (i + 1) + ": " + vectors[i]);
        }
    }
}
