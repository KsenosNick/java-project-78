package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class StringSchemaTest {
    Validator validator;
    StringSchema schema;

    @BeforeEach
    public void setup() {
        validator = new Validator();
        schema = validator.string();
    }

    @Test
    void testIsRequired() {
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testMinLength() {
        schema.minLength(0);
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.minLength(6);
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("wh"));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testContains() {
        schema.contains("what");
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid(null));

        schema.contains("does the fox ");
        assertTrue(schema.isValid("what does the fox say"));

        schema.contains("whatthe");
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    void testIsValidMultipleCalls() {
        schema.minLength(10).minLength(4);
        assertTrue(schema.isValid("Hexlet"));
        assertTrue(schema.isValid(null));

        schema.required().contains("what");
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid(null));
    }
}
