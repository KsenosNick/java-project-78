package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

public class StringSchema {
    private final Map<String, Predicate<String>> validations = new TreeMap<>();

    public StringSchema required() {
        String schemaKey = "required";
        Predicate<String> validation = value -> value != null && !value.isEmpty();
        this.validations.put(schemaKey, validation);
        return this;
    }

    public StringSchema minLength(int minLength) {
        String schemaKey = "minLength";
        Predicate<String> validation = value -> value.length() >= minLength;
        this.validations.put(schemaKey, validation);
        return this;
    }

    public StringSchema contains(String string) {
        String schemaKey = "contains";
        Predicate<String> validation = value -> value.contains(string);
        this.validations.put(schemaKey, validation);
        return this;
    }

    public boolean isValid(String s) {
        if ((s == null) && validations.containsKey("required")) {
            return false;
        }
        if (s == null) {
            return true;
        }

        return validations.values().stream()
                .allMatch(validation -> validation.test(s));
    }
}
