import java.io.IOException;
import java.util.Scanner;

class Program
{
    private static final String SHOPPING_LIST_FILE_PATH = "shoppingList.csv";
    private static final String PRODUCTS_CATALOG_FILE_PATH = "products.txt";

    private static ShoppingList shoppingList;
    private static FileIO shoppingListFile;
    private static ProductsCatalog catalog;

    public static void main(String[] args) {
        initializeProgram();
        runMenuLoop();
    }

    private static void initializeProgram() {
        try {
            shoppingListFile = new FileIO(SHOPPING_LIST_FILE_PATH);
            shoppingList = new ShoppingList(shoppingListFile.read());
            initializeCatalog();
        }
        catch (IOException e) {
            System.err.println("error: " + e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    private static void initializeCatalog() {
        try {
            catalog = new ProductsCatalog(PRODUCTS_CATALOG_FILE_PATH);
        }
        catch (Exception e) {
            System.out.println("couldn't load available products (" + e.getMessage() + ")");
            catalog = null;
        }
    }

    private static void runMenuLoop() {
        Scanner scanner = new Scanner(System.in);
        ShoppingListManager shoppingListManager = new ShoppingListManager(shoppingList, catalog, shoppingListFile);
        boolean isRunning = true;
        while (isRunning) {
            printMenuHeader();
            printMenu();
            System.out.print("> ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    shoppingListManager.displayAvailableProducts();
                    waitForUserInput();
                    break;
                case "2":
                    shoppingListManager.displayCurrentList();
                    waitForUserInput();
                    break;
                case "3":
                    shoppingListManager.addProductToShoppingList();
                    waitForUserInput();
                    break;
                case "4":
                    shoppingListManager.removeProductFromShoppingList();
                    waitForUserInput();
                    break;
                case "5":
                    shoppingListManager.saveShoppingListToFile();
                    waitForUserInput();
                    break;
                case "6":
                    printExitMessage();
                    return;
                default:
                    System.out.println("Invalid option. Choose 1-6.");
                    break;
            }
        }
        scanner.close();
    }

    private static void printMenuHeader() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("\t\t\tShopping List");
        System.out.println("========================================");
    }

    private static void printMenu() {
        System.out.println("1) Show available products (catalog)");
        System.out.println("2) Show shopping list");
        System.out.println("3) Add product");
        System.out.println("4) Remove product from list");
        System.out.println("5) Save shopping list");
        System.out.println("6) Exit");
    }

    private static void waitForUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
        System.out.println();
    }

    private static void printExitMessage() {
        System.out.println("Exiting...");
    }
}
