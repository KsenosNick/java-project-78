package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    public MapSchema<K, V> required() {
        String schemaKey = "required";
        Predicate<Map<K, V>> validation = Objects::nonNull;
        addValidation(schemaKey, validation);
        return this;
    }

    public MapSchema<K, V> sizeOf(int size) {
        String schemaKey = "sizeOf";
        Predicate<Map<K, V>> validation = map -> map.size() == size;
        addValidation(schemaKey, validation);
        return this;
    }
}
