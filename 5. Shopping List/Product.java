import java.util.Objects;

public final class Product
{
    private final String category;
    private final String item;

    public Product(String category, String item) {
        this.category = Objects.requireNonNull(category).trim();
        this.item = Objects.requireNonNull(item).trim();
    }

    public String getCategory() {
        return category;
    }

    public String getItem() {
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return category.equalsIgnoreCase(product.category) && item.equalsIgnoreCase(product.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category.toLowerCase(), item.toLowerCase());
    }

    @Override
    public String toString() {
        return category + " - " + item;
    }
}
