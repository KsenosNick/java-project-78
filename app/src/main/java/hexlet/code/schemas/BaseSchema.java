package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> validations = new HashMap<>();

    protected final void addValidation(String name, Predicate<T> validation) {
        validations.put(name, validation);
    };

    public final boolean isValid(T value) {
        if (value == null && validations.containsKey("required")) {
            return false;
        }
        if (value == null) {
            return true;
        }

        return validations.values().stream()
                .allMatch(validation -> validation.test(value));
    }
}
