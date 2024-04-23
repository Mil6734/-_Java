import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // обЪект класса для чтения ввода
        System.out.println("Введите выражение");
        String input = sc.nextLine(); // считывает введенное вырожение
        try {
            String result = calc(input);
            System.out.println("Результат: " +result);
        }catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage()); // если есть исключение
        }
    }
    public static String calc(String input) throws Exception{
        String[] part = input.split("[+\\-*/]"); // разбивает строку на части
        if(part.length != 2){
            throw new Exception("Некорректный формат выражения");
        }

        boolean isRoman = false; // римские или нет
        int a, b;
        try {
            a = Integer.parseInt(part[0].trim()); // преобразует в число, trim убирет пробелы
            b = Integer.parseInt(part[1].trim()); //
        }catch (Exception e){
            a = RomanNum.valueOf(part[0].trim()).getValue(); // значение римского числа
            b = RomanNum.valueOf(part[1].trim()).getValue();
            isRoman = true;
        }

        if (a < 1 || a > 10 || b < 1 || b > 10){
            throw new Exception("Числа должны бать в деапозоне от 1 до 10 включительно");
        }

        char operation = whatOperation(input); // получает операцию
        int result = 0; // результат
        switch (operation){
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            default:
                throw new Exception("Такой операции нет");
        }
        if(isRoman){
            if(result <= 0){
                throw new Exception("В римской системе нет 0 и отрецательных чисел");
            }
            return RomanNum.values()[result - 1].name(); // ввывод римкого числа -1
        }else {
            return String.valueOf(result); // ввывод строки
        }
    }
    public static char whatOperation(String input) throws Exception{
        for (char op : input.toCharArray()){ // перебор символов
            if(op == '+' || op == '-' || op == '*' || op == '/'){
                return op; // озвращает операцию
            }
        }
        throw new Exception("Нет такой операции");
    }
}