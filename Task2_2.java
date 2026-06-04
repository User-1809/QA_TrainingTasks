
import java.util.Arrays;

public class Task2_2 {

    public static void main(String[] args) {
        System.out.println("\nМетод printThreeWords:");
        printThreeWords();

        System.out.println("\nМетод checkSumSign:");
        checkSumSign();

        System.out.println("\nМетод printColor:");
        printColor();

        System.out.println("\nМетод compareNumbers:");
        compareNumbers();

        System.out.println("\nМетод checkSum:");
        System.out.println(checkSum(5, 7));

        System.out.println("\nМетод checkNumberPositive:");
        checkNumberPositive(5);

        System.out.println("\nМетод checkNumberNegative:");
        System.out.println(checkNumberNegative(-5));

        System.out.println("\nМетод printString:");
        printString("String", 2);

        System.out.println("\nМетод isLeapYear:");
        System.out.println(isLeapYear(2026));

        System.out.println("\nМетод invertBinaryArray:");
        invertBinaryArray();

        System.out.println("\nМетод fillArray:");
        fillArray();

        System.out.println("\nМетод multiplicationArray:");
        multiplicationArray();

        System.out.println("\nМетод fillSquareArray:");
        fillSquareArray(3);

        System.out.println("\nМетод createFilledArray:");
        int[] filledArr = createFilledArray(5, 1);
        System.out.println(Arrays.toString(filledArr));
    }

    public static void printThreeWords() { // 1. Три слова в столбец
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() { // 2. Знак суммы двух чисел
        int a = 15;
        int b = -20;
        if ((a + b) >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() { // 3. Цвет в зависимости от переменной
        int value = 50;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() { // 4. Сравнение двух чисел
        int a = 8;
        int b = 12;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static boolean checkSum(int a, int b) { // 5. Проверка попадания суммы в диапазон
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static void checkNumberPositive(int num) { // 6. Определение положительности числа
        if (num >= 0) {
            System.out.println("Положительное");
        } else {
            System.out.println("Отрицательное");
        }
    }

    public static boolean checkNumberNegative(int number) { // 7. Проверка числа на отрицательность

        return number < 0;
    }

    public static void printString(String str, int count) { // 8. Вывод строки заданное количество раз
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    public static boolean isLeapYear(int year) { // 9. Определение високосного года

        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void invertBinaryArray() { // 10. Рокировка 0 и 1 в массиве
        int[] arr = {1, 1, 0, 0, 1, 0};
        System.out.println(Arrays.toString(arr)); // вывод "до"
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }
        System.out.println(Arrays.toString(arr)); // вывод "после"
    }

    public static void fillArray() { // 11. Заполнение пустого массива
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void multiplicationArray() { // 12. Умножение на 2 элементов массива, которые меньше 6
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) { // вывод "до"
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(arr)); // вывод "после"
    }

    public static void fillSquareArray(int size) { // 13. Заполнение диагоналей квадратного массива
        int[][] square = new int[size][size];
        for (int i = 0; i < size; i++) {
            square[i][i] = 1;
            square[i][size - 1 - i] = 1;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(square[i]));
        }
    }

    public static int[] createFilledArray(int len, int initialValue) { // 14. Массив с заданными длиной и значениями
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }
}
