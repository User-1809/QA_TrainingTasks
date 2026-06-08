public class Product {

    private String name;               // поля
    private String productionDate;
    private String manufacturer;
    private String country;
    private int price;
    private boolean isReserved;

    public Product(String name, String productionDate,    //конструктор
                   String manufacturer, String country,
                   int price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void printInfo() {
        System.out.println("\n Товар: " + name);
        System.out.println(" Дата производства: " + productionDate);
        System.out.println(" Производитель: " + manufacturer);
        System.out.println(" Страна: " + country);
        System.out.println(" Цена: " + price);
        System.out.println(" Забронирован: " + (isReserved ? "Да" : "Нет"));
    }
}