import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ic1.TemperatureConverter;

public class TemperatureConverterTest {
    private final TemperatureConverter temperatureConverter = new TemperatureConverter();

    @Test
    public void fahrenheitToCelsiusPositive32(){
        assertEquals(0, temperatureConverter.fahrenheitToCelsius(32), 0);
    }

    @Test
    public void fahrenheitToCelsius0(){
        assertEquals(-17, temperatureConverter.fahrenheitToCelsius(0), 0.77778);
    }

    @Test
    public void fahrenheitToCelsiusNegative20(){
        assertEquals(-28, temperatureConverter.fahrenheitToCelsius(-20), 0.88889);
    }

    @Test
    public void kelvinToCelsiusPositive27315(){
        assertEquals(0, temperatureConverter.kelvinToCelsius(273.15), 0);
    }

    @Test
    public void kelvinToCelsius0(){
        assertEquals(-273.15, temperatureConverter.kelvinToCelsius(0), 0);
    }

    @Test
    public void kelvinToCelsiusNegative27315(){
        assertEquals(-546.3, temperatureConverter.kelvinToCelsius(-273.15), 0);
    }

    @Test
    public void celsiusToFahrenheit0(){
        assertEquals(32, temperatureConverter.celsiusToFahrenheit(0), 0);
    }

    @Test
    public void celsiusToFahrenheitPositive20(){
        assertEquals(68, temperatureConverter.celsiusToFahrenheit(20), 0);
    }

    @Test
    public void celsiusToFahrenheitPositive100(){
        assertEquals(212, temperatureConverter.celsiusToFahrenheit(100), 0);
    }

    @Test
    public void celsiusToFahrenheitNegative20(){
        assertEquals(-4, temperatureConverter.celsiusToFahrenheit(-20), 0);
    }

    @Test
    public void celsiusToFahrenheitNegative100(){
        assertEquals(-148, temperatureConverter.celsiusToFahrenheit(-100), 0);
    }

    @Test
    public void isExtremeTemperaturePositive(){
        assertEquals(true, temperatureConverter.isExtremeTemperature(100));
    }
    
    @Test
    public void isExtremeTemperatureNegative(){
        assertEquals(true, temperatureConverter.isExtremeTemperature(-100));
    }
    
    @Test
    public void isExtremeTemperaturePositive50(){
        assertEquals(false, temperatureConverter.isExtremeTemperature(50));
    }
    
    @Test
    public void isExtremeTemperatureNegative40(){
        assertEquals(false, temperatureConverter.isExtremeTemperature(-40));
    }
    
    @Test
    public void isExtremeTemperatureNeutral(){
        assertEquals(false, temperatureConverter.isExtremeTemperature(0));
    }
    
}
