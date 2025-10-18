package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MapSchemaTest {
    Validator validator;
    MapSchema<String, String> schema;
    Map<String, String> data;

    @BeforeEach
    public void setup() {
        validator = new Validator();
        schema = validator.map();
        data = new HashMap<>();
    }

    @Test
    void testIsRequired() {
        assertTrue(schema.isValid(data));
        assertTrue(schema.isValid(null));

        schema.required();
        assertTrue(schema.isValid(new HashMap<>()));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testSizeOf() {
        schema.sizeOf(1);

        assertFalse(schema.isValid(data));
        assertTrue(schema.isValid(null));

        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeOf(2);
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testIsValidMultipleCalls() {
        schema.sizeOf(2).sizeOf(1);

        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        data.put("key2", "value2");
        assertFalse(schema.isValid(data));
        assertTrue(schema.isValid(null));

        schema.required().sizeOf(2);
        assertTrue(schema.isValid(data));
        assertFalse(schema.isValid(null));
    }

}
