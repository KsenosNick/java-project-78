package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

    public final NumberSchema required() {
        String schemaKey = "required";
        Predicate<Integer> validation = Objects::nonNull;
        addValidation(schemaKey, validation);
        return this;
    }

    public final NumberSchema positive() {
        String schemaKey = "positive";
        Predicate<Integer> validation = number -> number > 0;
        addValidation(schemaKey, validation);
        return this;
    }

    public final NumberSchema range(int minValue, int maxValue) {
        String schemaKey = "range";
        Predicate<Integer> validation = number -> (number >= minValue) && (number <= maxValue);
        addValidation(schemaKey, validation);
        return this;
    }
}
