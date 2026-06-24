import java.util.ArrayList;

class Student {
    public String name;
    public String group;
    public int course;
    public ArrayList<Integer> grades; // Список оценок

    public Student(String name, String group, int course, ArrayList<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public double getAverageGrade() {
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
}

public class Task1 {

    // удаляем двоечников / переводим на следующий курс
    public static void processStudents(ArrayList<Student> list) {

        for (int i = list.size() - 1; i >= 0; i--) {
            Student s = list.get(i);

            if (s.getAverageGrade() < 3) {
                list.remove(i);
            } else {
                s.course++;
            }
        }
    }

    // вывод студентов, которые учатся на заданном курсе
    public static void printStudents(ArrayList<Student> students, int course) {
        System.out.println("Студенты на " + course + " курсе:");
        for (Student s : students) {
            if (s.course == course) {
                System.out.println("- " + s.name);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Student> studentsList = new ArrayList<>();

        ArrayList<Integer> ivanGrades = new ArrayList<>();
        ivanGrades.add(4); ivanGrades.add(5);

        ArrayList<Integer> petrGrades = new ArrayList<>();
        petrGrades.add(2); petrGrades.add(3);

        studentsList.add(new Student("Иван", "163", 1, ivanGrades));
        studentsList.add(new Student("Петр", "161", 1, petrGrades));

        processStudents(studentsList);

        printStudents(studentsList, 2);
    }
}
