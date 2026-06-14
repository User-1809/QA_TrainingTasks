class Animal { // класс Животное
    protected String name;
    protected int maxRun;
    protected int maxSwim;

    public static int animalCount = 0;

    public Animal(String name, int maxRun, int maxSwim) {
        this.name = name;
        this.maxRun = maxRun;
        this.maxSwim = maxSwim;
        animalCount++;
    }

    public void run(int distance) {
        if (distance <= maxRun) System.out.println(name + " пробежал " + distance + " м.");
        else System.out.println(name + " не пробежит " + distance + " м.");
    }

    public void swim(int distance) {
        if (maxSwim == 0) System.out.println(name + " не умеет плавать");
        else if (distance <= maxSwim) System.out.println(name + " проплыл " + distance + " м.");
        else System.out.println(name + " не проплывет " + distance + " м.");
    }
}

class Cat extends Animal { // Класс Кот
    public static int catCount = 0;

    public int appetite;
    public boolean isFull = false;

    public Cat(String name, int appetite) {
        super(name, 200, 0);
        this.appetite = appetite;
        catCount++;
    }

    public void eat(Plate plate) {
        if (plate.decreaseFood(appetite)) isFull = true;
    }
}

class Dog extends Animal { // класс Собака
    public static int dogCount = 0;
    public Dog(String name) {
        super(name, 500, 10);
        dogCount++;
    }
}

class Plate { // класс Миска
    private int food;
    public Plate(int food) { this.food = Math.max(food, 0); }
    public void addFood(int amount) { if (amount > 0) food += amount; }
    public void info() { System.out.println("Еды в миске: " + food); }

    public boolean decreaseFood(int amount) {
        if (food < amount) return false;
        food -= amount;
        return true;
    }
}

public class Task1 { // проверочный класс
    public static void main(String[] args) {
        Cat murzik = new Cat("Мурзик", 25);
        Dog bobik = new Dog("Бобик");

        bobik.run(150);
        murzik.swim(5);

        Plate plate = new Plate(40);
        Cat[] cats = { murzik, new Cat("Барсик", 15), new Cat("Пушок", 20) };

        System.out.println("\nКоты кушают:");
        for (Cat c : cats) c.eat(plate);
        for (Cat c : cats) System.out.println(c.name + " сыт: " + (c.isFull ? "Да" : "Нет"));
        plate.info();

        System.out.println("\nДобавление еды:");
        plate.addFood(30);
        for (Cat c : cats) if (!c.isFull) c.eat(plate);
        for (Cat c : cats) System.out.println(c.name + " сыт: " + (c.isFull ? "Да" : "Нет"));

        System.out.println("\nЖивотных: " + Animal.animalCount);
        System.out.println("Котов: " + Cat.catCount);
        System.out.println("Собак: " + Dog.dogCount);
    }
}

