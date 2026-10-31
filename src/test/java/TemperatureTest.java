
import org.junit.jupiter.api.Test;

import ic.Temperature;

import static org.junit.jupiter.api.Assertions.*;

public class TemperatureTest {
    @Test
    public void testConstructor_withoutIdAndTimestamp() {
        Temperature temperature = new Temperature("C", 100, true, "F", 123);

        assertEquals("C", temperature.getOriginalType());
        assertEquals(100, temperature.getOriginalDegree());
        assertEquals("F", temperature.getConvertedType());
        assertEquals(123, temperature.getConvertedDegree());
        assertEquals(true, temperature.getExtreme());
        assertEquals(0, temperature.getId()); // not set via this constructor
    }

    @Test
    public void testConstructor_withIdAndTimestamp() {
        Temperature temperature = new Temperature(1, "C", 100, true, "F", 123);

        assertEquals(1, temperature.getId());
        assertEquals("C", temperature.getOriginalType());
        assertEquals(100, temperature.getOriginalDegree());
        assertEquals("F", temperature.getConvertedType());
        assertEquals(123, temperature.getConvertedDegree());
        assertEquals(true, temperature.getExtreme());
    }
}
