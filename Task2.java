interface Figure { // интерфейс
    double getArea(); // метод для площади
    default double getPerimeter() { // дефолный метод для периметра
        return 0.0;
    }

    String getFillColor();
    String getBorderColor();

    default void printInfo() {
        System.out.println("\nФигура: " + getClass().getSimpleName());
        System.out.printf("  Периметр: %.2f\n", getPerimeter());
        System.out.printf("  Площадь: %.2f\n", getArea());
        System.out.println("  Цвет фона: " + getFillColor());
        System.out.println("  Цвет границ: " + getBorderColor());
    }
}

class Circle implements Figure { // класс Круг
    private double r;
    private String fill;
    private String border;

    public Circle(double r, String fill, String border) {
        this.r = r;
        this.fill = fill;
        this.border = border;
    }

    @Override
    public double getArea() { return Math.PI * r * r; }

    @Override
    public double getPerimeter() { return 2 * Math.PI * r; }

    @Override
    public String getFillColor() { return fill; }

    @Override
    public String getBorderColor() { return border; }
}

class Rectangle implements Figure { // класс Прямоугольнк
    private double w, h;
    private String fill;
    private String border;

    public Rectangle(double w, double h, String fill, String border) {
        this.w = w;
        this.h = h;
        this.fill = fill;
        this.border = border;
    }

    @Override
    public double getArea() { return w * h; }

    @Override
    public double getPerimeter() { return 2 * (w + h); }

    @Override
    public String getFillColor() { return fill; }

    @Override
    public String getBorderColor() { return border; }
}

class Triangle implements Figure { // класс Треугольник
    private double a, b, c;
    private String fill;
    private String border;

    public Triangle(double a, double b, double c, String fill, String border) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.fill = fill;
        this.border = border;
    }

    @Override
    public double getPerimeter() { return a + b + c; }

    @Override
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public String getFillColor() { return fill; }

    @Override
    public String getBorderColor() { return border; }
}

public class Task2 {
    public static void main(String[] args) {
        Figure[] figures = {
                new Circle(5, "Красный", "Черный"),
                new Rectangle(4, 6, "Синй", "Белый"),
                new Triangle(3, 4, 5, "Зеленый", "Желтый")
        };

        for (Figure f : figures) {
            f.printInfo();
        }
    }
}

