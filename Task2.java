import java.util.ArrayList;
import java.util.HashMap;

class PhoneBook {
    // храним фамилию (String) и список номеров (ArrayList<String>)
    private HashMap<String, ArrayList<String>> book = new HashMap<>();

    public void add(String surname, String phoneNumber) {
        if (!book.containsKey(surname)) {
            book.put(surname, new ArrayList<>());
        }
        book.get(surname).add(phoneNumber);
    }

    public ArrayList<String> get(String surname) {
        if (book.containsKey(surname)) {
            return book.get(surname);
        } else {
            return new ArrayList<>();
        }
    }
}

public class Task2 {
    public static void main(String[] args) {
        PhoneBook myBook = new PhoneBook();

        myBook.add("Иванов", "89991112233");
        myBook.add("Петров", "89991114455");
        myBook.add("Иванов", "89991116677");

        String searchSurname = "Иванов";
        ArrayList<String> ivanovPhones = myBook.get(searchSurname);

        System.out.println("Номера по фамилии " + searchSurname + ":");
        for (String phone : ivanovPhones) {
            System.out.println("-" + phone);
        }
    }
}