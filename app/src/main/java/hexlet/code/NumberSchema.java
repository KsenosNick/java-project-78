package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Predicate;

public class NumberSchema {
    private final Map<String, Predicate<Integer>> validations = new TreeMap<>();

    public NumberSchema required() {
        String schemaKey = "required";
        Predicate<Integer> validation = Objects::nonNull;
        this.validations.put(schemaKey, validation);
        return this;
    }

    public NumberSchema positive() {
        String schemaKey = "positive";
        Predicate<Integer> validation = number -> number > 0;
        this.validations.put(schemaKey, validation);
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        String schemaKey = "range";
        Predicate<Integer> validation = number -> (number >= minValue) && (number <= maxValue);
        this.validations.put(schemaKey, validation);
        return this;
    }

    public boolean isValid(Integer number) {
        if ((number == null) && validations.containsKey("required")) {
            return false;
        }
        if (number == null) {
            return true;
        }

        return validations.values().stream()
                .allMatch(validation -> validation.test(number));
    }
}
