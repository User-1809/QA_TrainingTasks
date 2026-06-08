public class Main {
    public static void main(String[] args) {
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Samsung S25", "01.02.2025", "Samsung", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 16", "10.09.2024", "Apple", "USA", 6200, false);
        productsArray[2] = new Product("PlayStation 5", "15.11.2024", "Sony", "Japan", 3500, true);
        productsArray[3] = new Product("MacBook Air", "20.03.2024", "Apple", "USA", 7500, false);
        productsArray[4] = new Product("Xiaomi 14", "15.04.2024", "Xiaomi", "China", 4200, false);

        for (int i = 0; i < productsArray.length; i++) {
            productsArray[i].printInfo();
        }
    }
}