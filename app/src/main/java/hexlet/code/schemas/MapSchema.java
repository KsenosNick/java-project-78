package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public final MapSchema required() {
        String schemaKey = "required";

        Predicate<Map<?, ?>> validation = Objects::nonNull;
        addValidation(schemaKey, validation);

        return this;
    }

    public final MapSchema sizeof(int size) {
        String schemaKey = "sizeof";

        Predicate<Map<?, ?>> validation = map -> map.size() == size;
        addValidation(schemaKey, validation);

        return this;
    }

    public final void shape(Map<?, ? extends BaseSchema<?>> schemas) {
        String schemaKey = "shape";

        Predicate<Map<?, ?>> validation = map -> {
            if (map == null) {
                return true;
            }

            return schemas.entrySet().stream()
                    .allMatch(entry -> {
                        Object key = entry.getKey();
                        BaseSchema<Object> schema = (BaseSchema<Object>) entry.getValue();
                        Object value = map.get(key);

                        return schema.isValid(value);
                    });
        };

        addValidation(schemaKey, validation);
    }
}
