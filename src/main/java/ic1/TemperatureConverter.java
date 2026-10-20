package ic1;

import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter a number");
        double a = sc.nextDouble();
        sc.close();
        TemperatureConverter converter = new TemperatureConverter();
        System.out.println("fahrenheit to celsius");
        System.out.println(converter.fahrenheitToCelsius(a));
        System.out.println("kelvin to celsius");
        System.out.println(converter.kelvinToCelsius(a));
        System.out.println("celsius to fahrenheit");
        System.out.println(converter.celsiusToFahrenheit(a));
        System.out.println("is extreme temperature");
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