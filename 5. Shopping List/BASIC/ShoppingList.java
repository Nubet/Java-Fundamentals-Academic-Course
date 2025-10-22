import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingList
{
    private final Map<String, List<String>> shoppingList;

    public ShoppingList(List<String> csvList) {
        this.shoppingList = new HashMap<>();
        if (csvList == null || csvList.isEmpty()) {
            return;
        }

        for (var rawLine : csvList) {
            String line = rawLine.trim();

            var parts = line.split(",");
            String category = parts[0].trim();
            List<String> productsInCategory = new ArrayList<>();
            for (int i = 1; i < parts.length; i++) {
                productsInCategory.add(parts[i].trim());
            }
            this.shoppingList.put(category, productsInCategory);
        }
    }

    public void addProduct(String category, String product) {
        if (category == null || product == null)
            throw new IllegalArgumentException("Category and product cannot be null.");

        String c = category.trim();
        String p = product.trim();
        if (c.isEmpty() || p.isEmpty())
            throw new IllegalArgumentException("Category and product cannot be empty.");

        List<String> products = this.shoppingList.get(c);
        if (products == null) {
            products = new ArrayList<>();
            this.shoppingList.put(c, products);
        }
        if (products.contains(p))
            throw new IllegalStateException("Product '" + p + "' is already on the shopping list under '" + c + "'.");
        products.add(p);
    }

    public void removeProduct(String category, String product) {
        if (category == null || product == null)
            throw new IllegalArgumentException("Category and product cannot be null.");

        String c = category.trim();
        String p = product.trim();
        if (c.isEmpty() || p.isEmpty())
            throw new IllegalArgumentException("Category and product cannot be empty.");

        List<String> products = this.shoppingList.get(c);
        if (products == null)
            throw new IllegalArgumentException("Category not found: '" + c + "'.");

        if (!products.remove(p))
            throw new IllegalStateException("Product not found in the selected category.");

    }

    public boolean isEmpty() {
        if (this.shoppingList.isEmpty())
            return true;

        for (var products : this.shoppingList.values()) {
            if (products != null && !products.isEmpty())
                return false;
        }
        return true;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Shopping list is empty.");
            return;
        }
        for (var category : this.shoppingList.keySet()) {
            System.out.println(category);
            for (var product : this.shoppingList.get(category)) {
                System.out.println("\t- " + product);
            }
        }
    }

    public List<String> toList() {
        List<String> list = new ArrayList<>();
        for (var category : this.shoppingList.keySet()) {
            var products = this.shoppingList.get(category);
            StringBuilder sb = new StringBuilder(category);
            for (var product : products) {
                sb.append(',').append(product);
            }
            list.add(sb.toString());
        }

        return list;
    }
}
