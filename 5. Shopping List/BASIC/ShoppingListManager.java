import java.io.IOException;
import java.util.Scanner;

public class ShoppingListManager
{
    private final ShoppingList shoppingList;
    private final ProductsCatalog catalog;
    private final FileIO file;

    public ShoppingListManager(ShoppingList shoppingList, ProductsCatalog catalog, FileIO file) {
        this.shoppingList = shoppingList;
        this.catalog = catalog;
        this.file = file;
    }

    public void displayAvailableProducts() {
        if (catalog == null) {
            System.out.println("No available products catalog loaded.");
            return;
        }
        catalog.printAvailableProducts();
    }

    public void displayCurrentList() {
        shoppingList.print();
    }

    public void addProductToShoppingList() {
        Scanner scanner = new Scanner(System.in);
        if (catalog == null) {
            System.out.println("Cannot add products: Product catalog is not available.");
            return;
        }

        System.out.println("Available products:");
        catalog.printAvailableProducts();

        System.out.print("Enter category: ");
        String category = scanner.nextLine().trim();
        System.out.print("Enter product: ");
        String product = scanner.nextLine().trim();

        try {
            if (category.isEmpty() || product.isEmpty())
                throw new IllegalArgumentException("Category and product cannot be empty.");

            if (!catalog.hasItem(category, product))
                throw new IllegalArgumentException("Could not add '" + product + "' because it is not listed under category '" + category + "' in available products.");

            shoppingList.addProduct(category, product);
            System.out.println("Added: " + product + " under '" + category + "'.");
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeProductFromShoppingList() {
        Scanner scanner = new Scanner(System.in);
        try {
            if (shoppingList.isEmpty())
                throw new IllegalStateException("Shopping list is empty.");

            System.out.print("Enter category: ");
            String category = scanner.nextLine().trim();
            if (category.isEmpty())
                throw new IllegalArgumentException("Category cannot be empty.");

            System.out.print("Enter product: ");
            String product = scanner.nextLine().trim();
            if (product.isEmpty())
                throw new IllegalArgumentException("Product cannot be empty.");

            shoppingList.removeProduct(category, product);
            System.out.println("Removed: " + product + " from '" + category + "'.");
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveShoppingListToFile() {
        try {
            file.write(shoppingList.toList());
            System.out.println("The file has been saved");
        }
        catch (IOException e) {
            System.out.println("Failed to save shopping list: " + e.getMessage());
        }
    }
}
