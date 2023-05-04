import java.util.Scanner;

class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа (арабских или римских): ");
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    static String calc(String input) throws Exception {
        int operand1;
        int operand2;
        String oper;
        String result;
        boolean isRoman;

        //Строка может придти как с пробелами, так и без
        input = input.replaceAll("\\s", "");

        String[] operands = input.split("[+\\-*/]");
        oper = detectOperation(input);

        if (operands.length > 2) throw new Exception(
                "Ошибка: формат математической операции не удовлетворяет заданию "
                        + "- два операнда и один оператор (+, -, /, *)");
        else if (operands.length < 2) throw new Exception("Ошибка: строка не является математической операцией");

        //если оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            //конвертация операндов в арабские цифры
            operand1 = Roman.convertToArabian(operands[0]);
            operand2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        //если оба числа арабские
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            operand1 = Integer.parseInt(operands[0]);
            operand2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        //если одни число римское, а другое - арабское
        else {
            throw new Exception("Ошибка: одновременно используются разные системы счисления");
        }

        if(operand1 <= 0 || operand2 <= 0 || operand1 > 10 || operand2 > 10)
            throw new Exception("Ошибка: числа должны быть от 1 до 10");

        int arabian = calculate(operand1, operand2, oper);
        if (isRoman) {
            if (arabian < 0)
                throw new Exception("Ошибка: в римской системе счисления нет отрицательных чисел.");
            else if (arabian == 0)
                throw new Exception("Ошибка: в римской системе счисления нет нуля");

            //конвертируем результат операции из арабского в римское
            result = Roman.convertToRoman(arabian);
        }

        else {
            //Конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        //возвращаем результат
        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calculate(int a, int b, String oper) {
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }
}

class Roman {
    static String[] romanArray = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i]))
                return true;
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i]))
                return i;
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}