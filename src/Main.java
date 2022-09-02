import javax.print.DocFlavor;
import javax.swing.*;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static String calc(String input) {
        Integer results = 0;
        String[] parse = input.split(" ");
        switch (parse[1]) {
            case "+":
                results= Integer.parseInt(parse[0]) + Integer.parseInt(parse[2]);
                break;
            case "-":
                results = Integer.parseInt(parse[0]) - Integer.parseInt(parse[2]);
                break;
            case "/":
                results = Integer.parseInt(parse[0]) / Integer.parseInt(parse[2]);
                break;
            case "*":
                results = Integer.parseInt(parse[0]) * Integer.parseInt(parse[2]);
                break;
        }
        return results.toString();
    }
    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;
        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " не может быть преобразован в римскую цифру");
        }
        return result;
    }
    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " в римской системе нет отрицательных чисел");
        }
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    public static void main(String[] args) {
        Scanner inputstr = new Scanner(System.in);
        String input = inputstr.nextLine();
        String[] parse = input.split(" ");
        if (parse.length<3){
            throw new IllegalArgumentException("строка не является математической операцией");
        } else if (parse.length>3) {
            throw new IllegalArgumentException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        else {
            if ((isNumeric(parse[0])==true)&&(isNumeric(parse[2])==true))
            {
                System.out.println(calc(input));
            }
            else if (isNumeric(parse[0])==false&&(isNumeric(parse[2])==false))
            {
                Integer first, second;
                first = romanToArabic(parse[0]);
                second = romanToArabic(parse[2]);
                String par = first.toString() + " "+parse[1]+" " + second.toString();
                System.out.println(arabicToRoman(Integer.parseInt(calc(par))));
            }
            else
            {
                throw new IllegalArgumentException("Используются одновременно разные системы счисления");
            }
        }
    }
}














