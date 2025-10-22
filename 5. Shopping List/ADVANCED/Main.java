import java.nio.file.Path;

public final class Main
{
    public static void main(String[] args) throws Exception {
        Path catalogFilePath = Path.of(args.length > 0 ? args[0] : "products.txt");
        Path shoppingListFilePath = Path.of(args.length > 1 ? args[1] : "shopping_list.csv");

        MenuController controller = MenuController.create(catalogFilePath, shoppingListFilePath);
        controller.runMenuLoop();
    }
}
