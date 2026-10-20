package ic;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //prompt user for localization
        System.out.println("select language");
        System.out.println("[1] English (default)");
        System.out.println("[2] Finnish");
        System.out.println("[3] Swedish");
        System.out.println("[4] Japanese");
        int langSelect = sc.nextInt();

        //load localization
        Locale locale = new Locale("en", "US"); //default localization
        switch (langSelect) {
            case 2:
                locale = new Locale("fi", "FI");
                break;
            case 3:
                locale = new Locale("sv", "SE");
                break;
            case 4:
                locale = new Locale("ja", "JP");
                break;
            
            default:
                break;
        }

        ResourceBundle rb = ResourceBundle.getBundle("Messages_bundle", locale);

        //user input for temperature calculation
        System.out.println(rb.getString("prompt"));
        double a = sc.nextDouble();
        sc.close();
        TemperatureConverter converter = new TemperatureConverter();
        System.out.println(rb.getString("f2c"));
        System.out.println(converter.fahrenheitToCelsius(a));
        System.out.println(rb.getString("k2c"));
        System.out.println(converter.kelvinToCelsius(a));
        System.out.println(rb.getString("c2f"));
        System.out.println(converter.celsiusToFahrenheit(a));
        System.out.println(rb.getString("extreme"));
        System.out.println(converter.isExtremeTemperature(a));
    }
    public double fahrenheitToCelsius(double fahrenheit){
        return ((fahrenheit - 32) * 5 / 9);
    }

    public double kelvinToCelsius(double kelvin) {
        return (kelvin - 273.15);
    }

    public double celsiusToFahrenheit(double celsius) {
        return ((celsius * 9 / 5) + 32);
    }

    public boolean isExtremeTemperature(double celsius) {
        boolean extreme = false;
        if (celsius < -40) {
            extreme = true;
        } else if (celsius > 50) {
            extreme = true;
        }

        return extreme;
    }
}