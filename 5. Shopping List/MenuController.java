import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public final class MenuController
{
    private final ShoppingList shoppingListManager;
    private final Scanner scanner;

    public MenuController(ShoppingList shoppingListManager, Scanner scanner) {
        this.shoppingListManager = shoppingListManager;
        this.scanner = scanner;
    }

    public static MenuController create(Path catalogFilePath, Path shoppingListFilePath) throws IOException {
        ShoppingList s = new ShoppingList(catalogFilePath, shoppingListFilePath);
        s.loadProductCatalog();
        s.loadShoppingListIfExists();
        return new MenuController(s, new Scanner(System.in));
    }

    public void runMenuLoop() {
        while (true) {
            printMenuHeader();
            printMenu();
            int choice = readValidInt("Choose option", 1, 7);
            switch (choice) {
                case 1:
                    displayCatalog();
                    waitForUserInput();
                    break;
                case 2:
                    displayCurrentList();
                    waitForUserInput();
                    break;
                case 3:
                    handleAddProduct();
                    waitForUserInput();
                    break;
                case 4:
                    handleRemoveProduct();
                    waitForUserInput();
                    break;
                case 5:
                    saveShoppingListToFile();
                    waitForUserInput();
                    break;
                case 6:
                    reloadCatalogFromFile();
                    waitForUserInput();
                    break;
                case 7:
                    exitApplication();
                    return;
                default:
                    break;
            }
        }
    }

    private void printMenuHeader() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("\t\t\tShopping List");
        System.out.println("========================================");
    }

    private void printMenu() {
        System.out.println("1) Show available products");
        System.out.println("2) Show shopping list");
        System.out.println("3) Add product by selection");
        System.out.println("4) Remove product from list");
        System.out.println("5) Save shopping list");
        System.out.println("6) Reload catalog from file");
        System.out.println("7) Exit");
    }

    private void displayCatalog() {
        List<String> categories = shoppingListManager.getCategories();
        if (categories.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }
        for (String c : categories) {
            System.out.println(c);
            List<Product> items = shoppingListManager.getItemsInCategory(c);
            for (Product p : items) {
                System.out.println("  " + p.getItem());
            }
        }
    }

    private void displayCurrentList() {
        List<Product> list = shoppingListManager.getCurrentShoppingList();
        if (list.isEmpty()) {
            System.out.println("Shopping list is empty.");
            return;
        }
        System.out.println("Items on your shopping list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ") " + list.get(i));
        }
        System.out.println("Total: " + list.size());
    }

    private void handleAddProduct() {
        List<String> categories = shoppingListManager.getCategories();
        if (categories.isEmpty()) {
            System.out.println("No categories available.");
            return;
        }

        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ") " + categories.get(i));
        }

        int selectedCategoryIndex = readValidInt("Pick productCategory", 1, categories.size()) - 1;
        String selectedCategory = categories.get(selectedCategoryIndex);

        List<Product> items = shoppingListManager.getItemsInCategory(selectedCategory);
        if (items.isEmpty()) {
            System.out.println("No items in this productCategory.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ") " + items.get(i).getItem());
        }

        int selectedItemIndex = readValidInt("Pick item", 1, items.size()) - 1;
        Product selectedProduct = items.get(selectedItemIndex);
        if (shoppingListManager.contains(selectedProduct)) {
            System.out.println("Already on your list: " + selectedProduct);
            return;
        }
        shoppingListManager.add(selectedProduct);
        System.out.println("Added: " + selectedProduct + ". Items on list: " + shoppingListManager.getCurrentShoppingList().size());
    }


    private void handleRemoveProduct() {
        List<Product> shoppingList = shoppingListManager.getCurrentShoppingList();
        if (shoppingList.isEmpty()) {
            System.out.println("Shopping list is empty.");
            return;
        }

        for (int i = 0; i < shoppingList.size(); i++) {
            System.out.println((i + 1) + ") " + shoppingList.get(i));
        }

        int selectedItemIndex = readValidInt("Pick item to remove", 1, shoppingList.size()) - 1;
        Product selectedProduct = shoppingList.get(selectedItemIndex);

        shoppingListManager.remove(selectedProduct);
        System.out.println("Removed: " + selectedProduct + ". Items on list: " + shoppingListManager.getCurrentShoppingList().size());
    }

    private void saveShoppingListToFile() {
        try {
            shoppingListManager.saveShoppingList();
            System.out.println("Shopping list saved.");
        } catch (IOException e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    private void reloadCatalogFromFile() {
        try {
            shoppingListManager.loadProductCatalog();
            System.out.println("Catalog reloaded.");
        } catch (IOException e) {
            System.out.println("Reload failed: " + e.getMessage());
        }
    }

    private void exitApplication() {
        System.out.println("Exiting application...");
    }

    private int readValidInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + " [" + min + "-" + max + "]: ");
            String s = scanner.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v < min || v > max) {
                    System.out.println("Enter a number in range.");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    private void waitForUserInput() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
        System.out.println();
    }
}
