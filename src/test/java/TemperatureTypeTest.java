
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ic.TemperatureType;

public class TemperatureTypeTest {
    @Test
    public void testConstructorAndGetters() {
        TemperatureType type = new TemperatureType(1, "C");
        assertEquals(1, type.getId());
        assertEquals("C", type.getTypeName());
    }

    @Test
    public void testToString_returnsTypeName() {
        TemperatureType type = new TemperatureType(2, "F");
        assertEquals("F", type.toString());
    }
}
