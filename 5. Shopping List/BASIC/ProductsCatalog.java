import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsCatalog
{
    private final Map<String, List<String>> catalog = new HashMap<>();

    public ProductsCatalog(String filePath) throws IOException {
        loadCatalogFromFile(filePath);
    }

    private void loadCatalogFromFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            throw new IOException("Products catalog file not found: " + filePath);
        }

        List<String> lines = Files.readAllLines(path);
        String currentCategory = null;

        for (String line : lines) {
            line = line.trim();

            if (line.isEmpty()) continue;

            if (line.endsWith(":")) {
                currentCategory = line
                    .substring(0, line.length() - 1)
                    .trim();

                catalog.put(currentCategory, new ArrayList<>());
                continue;
            }
            if (currentCategory == null) continue;

            List<String> currentCategoryProducts = catalog.get(currentCategory);
            currentCategoryProducts.add(line);
        }
    }

    public void printAvailableProducts() {
        if (catalog.isEmpty()) {
            System.out.println("No available products found.");
            return;
        }
        for (var categoryKeyValuePair : catalog.entrySet()) {
            String categoryName = categoryKeyValuePair.getKey();
            List<String> productsInCategory = categoryKeyValuePair.getValue();

            System.out.println(categoryName);
            for (String product : productsInCategory) {
                System.out.println("\t- " + product);
            }
        }
    }
    public boolean hasCategory(String category) {
        return catalog.containsKey(category);
    }

    public boolean hasItem(String category, String item) {
        return hasCategory(category) && catalog.get(category).contains(item);
    }
}

