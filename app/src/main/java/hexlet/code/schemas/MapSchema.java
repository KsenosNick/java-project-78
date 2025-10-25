package hexlet.code.schemas;

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

    public void shape(Map<String, BaseSchema<String>> schemas) {
        String schemaKey = "shape";

        Predicate<Map<K, V>> validation = map -> {
            if (map == null) {
                return true;
            }

            return schemas.entrySet().stream()
                    .allMatch(entry -> {
                        String key = entry.getKey();
                        BaseSchema<String> schema = entry.getValue();
                        String value = (String) map.get(key);

                        return schema.isValid(value);
                    });
        };

        addValidation(schemaKey, validation);
    }
}
