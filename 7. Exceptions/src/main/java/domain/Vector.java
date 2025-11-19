package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vector
{
    private final List<Integer> components;

    public Vector(List<Integer> data) {
        this.components = new ArrayList<>(Objects.requireNonNull(data, "components cannot be null"));
    }

    public int getLength() {
        return components.size();
    }

    public int getComponent(int index) {
        return components.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < components.size(); i++) {
            sb.append(components.get(i));
            if (i < components.size() - 1)
                sb.append(",");
        }
        sb.append(")");
        return sb.toString();
    }
}
