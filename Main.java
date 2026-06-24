public class Main {

    public static void main(String[] args) {

        String[][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] wrongSizeArray = {
                {"1", "2"},
                {"3", "4"}
        };

        String[][] wrongDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "семь", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        // обработка исключений
        System.out.println("Правильный массив");
        try {
            int result = summArray(correctArray);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nМассив неверного размера");
        try {
            summArray(wrongSizeArray);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nМассив с некорректными данными");
        try {
            summArray(wrongDataArray);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nГенерация и поимка ArrayIndexOutOfBoundsException");
        try {
            int[] smallArray = {1, 2, 3};

            int ghost = smallArray[5];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймали исключение выхода за границы массива: " + e);
        }
    }

    public static int summArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {

        if (arr.length != 4) {
            throw new MyArraySizeException("Размер массива должен быть строго 4х4");
        }

        for (int i = 0; i < 4; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException("Размер массива должен быть строго 4х4");
            }
        }

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка в ячейке [" + i + "][" + j + "]: значение '" + arr[i][j] + "' является некорректным");
                }
            }
        }

        return sum;
    }
}

// исключение для размера массива
class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

// исключение для неверных данных
class MyArrayDataException extends Exception {
    public MyArrayDataException(String message) {
        super(message);
    }
}