package ic;

public class TemperatureConverter {
    
    public static double fahrenheitToCelsius(double fahrenheit){
        return ((fahrenheit - 32) * 5 / 9);
    }

    public static double kelvinToCelsius(double kelvin) {
        return (kelvin - 273.15);
    }

    public static double celsiusToFahrenheit(double celsius) {
        return ((celsius * 9 / 5) + 32);
    }

    public static boolean isExtremeTemperature(double celsius) {
        boolean extreme = false;
        if (celsius < -40) {
            extreme = true;
        } else if (celsius > 50) {
            extreme = true;
        }

        return extreme;
    }
}