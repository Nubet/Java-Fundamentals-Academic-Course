import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class ShoppingList
{
    private final Path catalogFilePath;
    private final Path shoppingListFilePath;

    private final List<Product> productCatalog = new ArrayList<>();
    private final Map<String, List<Product>> byCategory = new LinkedHashMap<>();
    private final LinkedHashSet<Product> currentShoppingList = new LinkedHashSet<>();

    public ShoppingList(Path catalogFilePath, Path shoppingListFilePath) {
        this.catalogFilePath = Objects.requireNonNull(catalogFilePath);
        this.shoppingListFilePath = Objects.requireNonNull(shoppingListFilePath);
    }

    public void loadProductCatalog() throws IOException {
        if (!Files.exists(catalogFilePath)) {
            throw new IOException("Catalog file not found: " + catalogFilePath);
        }
        productCatalog.clear();
        byCategory.clear();

        Files.readAllLines(catalogFilePath, StandardCharsets.UTF_8)
            .stream()
            .filter(line -> !line.strip().isEmpty())
            .forEach(line -> {
                if (!Character.isWhitespace(line.charAt(0)))
                    addCategoryFromLine(line);
                else
                    addProductFromLine(line);
            });
    }

    private void addCategoryFromLine(String line) {
        if (!line.endsWith(":")) {
            throw new RuntimeException("Invalid category header: " + line);
        }
        String category = line.substring(0, line.length() - 1).strip();
        byCategory.putIfAbsent(category, new ArrayList<>());
    }

    private void addProductFromLine(String line) {
        if (byCategory.isEmpty()) {
            throw new RuntimeException("Item without category: " + line);
        }
        String currentCategory = byCategory.keySet().iterator().next();
        Product product = new Product(currentCategory, line.strip());
        productCatalog.add(product);
        byCategory.get(currentCategory).add(product);
    }

    public void loadShoppingListIfExists() throws IOException {
        currentShoppingList.clear();
        if (!Files.exists(shoppingListFilePath)) return;

        Files.readAllLines(shoppingListFilePath, StandardCharsets.UTF_8)
            .stream()
            .filter(currentLine -> !currentLine.strip().isEmpty())
            .forEach(currentLine -> {
                String[] productParts = currentLine.split(",", 2);
                if (productParts.length != 2) {
                    throw new RuntimeException("Invalid CSV line: " + currentLine);
                }
                String category = productParts[0].strip();
                String productName = productParts[1].strip();
                currentShoppingList.add(new Product(category, productName));
            });
    }


    public void saveShoppingList() throws IOException {
        List<String> out = currentShoppingList.stream()
            .map(p -> p.getCategory() + "," + p.getItem())
            .toList();
        Files.write(shoppingListFilePath, out, StandardCharsets.UTF_8);
    }

    public List<String> getCategories() {
        return new ArrayList<>(byCategory.keySet());
    }

    public List<Product> getItemsInCategory(String category) {
        return Collections.unmodifiableList(byCategory.getOrDefault(category, List.of()));
    }

    public List<Product> getProductCatalog() {
        return Collections.unmodifiableList(productCatalog);
    }

    public List<Product> getCurrentShoppingList() {
        return new ArrayList<>(currentShoppingList);
    }

    public boolean add(Product p) {
        return currentShoppingList.add(p);
    }

    public boolean remove(Product p) {
        return currentShoppingList.remove(p);
    }

    public boolean contains(Product p) {
        return currentShoppingList.contains(p);
    }

    public boolean isEmpty() {
        return currentShoppingList.isEmpty();
    }
}
