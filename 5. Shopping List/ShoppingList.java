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
import java.util.stream.Collectors;

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
        productCatalog.clear();
        byCategory.clear();

        if (!Files.exists(catalogFilePath)) {
            throw new IOException("Catalog file not found: " + catalogFilePath);
        }

        List<String> catalogLines  = Files.readAllLines(catalogFilePath, StandardCharsets.UTF_8);
        String currentCategory = null;

        for (String rawLine : catalogLines) {
            String processedLine = rawLine.replace("\t", "    ");
            if (processedLine.trim().isEmpty()) continue;

            if (!Character.isWhitespace(processedLine.charAt(0))) {
                if (!processedLine.endsWith(":")) {
	                throw new IOException("Invalid category header: " + rawLine);
                }
                currentCategory = processedLine.substring(0, processedLine.length() - 1).trim();
                byCategory.putIfAbsent(currentCategory, new ArrayList<>());
            }
            else {
                if (currentCategory == null) {
	                throw new IOException("Item without category: " + rawLine);
                }
                String item = processedLine.trim();
                Product p = new Product(currentCategory, item);
                productCatalog.add(p);
                byCategory.get(currentCategory).add(p);
            }
        }
    }

    public void loadShoppingListIfExists() throws IOException {
        currentShoppingList.clear();
        if (!Files.exists(shoppingListFilePath)) return;

        List<String> shoppingListLines  = Files.readAllLines(shoppingListFilePath, StandardCharsets.UTF_8);
        for (int i = 0; i < shoppingListLines .size(); i++) {
            String currentLine = shoppingListLines .get(i).trim();
            if (currentLine.isEmpty()) continue;
            String[] productParts = currentLine.split(",", 2);
            if (productParts.length != 2) {
                throw new IOException("Invalid CSV at line " + (i + 1) + ": " + currentLine);
            }
            String category = productParts[0].trim();
            String productName = productParts[1].trim();

            Product p = new Product(category, productName);
            currentShoppingList.add(p);
        }
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
