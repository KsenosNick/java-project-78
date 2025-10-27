package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class NumberSchemaTest {
    private NumberSchema schema;

    @BeforeEach
    public void setup() {
        Validator validator = new Validator();
        schema = validator.number();
    }

    @Test
    void testIsRequired() {
        assertTrue(schema.isValid(-10));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(null));

        schema.required();
        assertTrue(schema.isValid(-10));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testIsPositive() {
        schema.positive();
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-10));
    }

    @Test
    void testRange() {
        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(1));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testIsValidMultipleCalls() {
        schema.range(5, 10).range(15, 20);
        assertTrue(schema.isValid(15));
        assertTrue(schema.isValid(20));
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(5));
        assertFalse(schema.isValid(10));

        schema.required().positive();
        assertTrue(schema.isValid(15));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(null));
    }
}
