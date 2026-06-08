public class Park {
    private String parkName;

    public Park(String parkName) {
        this.parkName = parkName;
    }

    public class Attraction {            //внутренни класс
        private String name;
        private String workingHours;
        private int price;

        public Attraction(String name, String workingHours, int price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void printInfo() {
            System.out.println("\nПарк: " + parkName);
            System.out.println("Аттракцион: " + name + " | Время работы: " + workingHours + " | Цена: " + price);
        }
    }
}