package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {

    public final StringSchema required() {
        String schemaKey = "required";
        Predicate<String> validation = value -> value != null && !value.isEmpty();
        addValidation(schemaKey, validation);
        return this;
    }

    public final StringSchema minLength(int minLength) {
        String schemaKey = "minLength";
        Predicate<String> validation = value -> value.length() >= minLength;
        addValidation(schemaKey, validation);
        return this;
    }

    public final StringSchema contains(String string) {
        String schemaKey = "contains";
        Predicate<String> validation = value -> value.contains(string);
        addValidation(schemaKey, validation);
        return this;
    }
}
