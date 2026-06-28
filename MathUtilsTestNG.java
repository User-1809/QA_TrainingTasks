import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class MathUtilsTestNG {

    public static class MathUtils {

        // факториал
        public static int factorial(int n) {
            int result = 1;
            for (int i = 1; i <= n; i++) result *= i;
            return result;
        }

        // площадь треугольника
        public static double triangleArea(double b, double h) {
            return 0.5 * b * h;
        }

        // арифметика
        public static int add(int a, int b) { return a + b; }
        public static int sub(int a, int b) { return a - b; }
        public static int mul(int a, int b) { return a * b; }
        public static double div(int a, int b) { return (double) a / b; }

        // сравнение
        public static String compare(int a, int b) {
            if (a > b) return ">";
            if (a < b) return "<";
            return "=";
        }
    }

    // ТЕСТЫ

    @Test
    public void testFactorial() {
        assertEquals(MathUtils.factorial(5), 120);
    }

    @Test
    public void testTriangleArea() {
        assertEquals(MathUtils.triangleArea(4, 5), 10.0);
    }

    @Test
    public void testArithmetic() {
        assertEquals(MathUtils.add(2, 3), 5);
        assertEquals(MathUtils.sub(7, 3), 4);
        assertEquals(MathUtils.mul(4, 3), 12);
        assertEquals(MathUtils.div(5, 2), 2.5);
    }

    @Test
    public void testCompare() {
        assertEquals(MathUtils.compare(5, 3), ">");
        assertEquals(MathUtils.compare(2, 8), "<");
        assertEquals(MathUtils.compare(4, 4), "=");
    }
}
