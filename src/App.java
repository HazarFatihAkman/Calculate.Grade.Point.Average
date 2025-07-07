
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Lesson;
import models.LessonType;
import models.Student;

public class App {
    public static void main(String[] args) throws Exception {
        List<Student> students = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int page = 0;
        while (page == 0) {
            System.out.println("------------------");
            System.out.println("| New Student (1) | Student List (2)");
            page = sc.nextInt();

            while (page < 0 || page > 2) {
                System.out.println("| New Student (1) | Student List (2)");
                page = sc.nextInt();
            }
            System.out.println("------------------");

            if (page == 1) {
                page = page_1(sc, students);
            }
            else if (page == 2) {
                page_2(students);
                page = 0;
            }
        }
        sc.close();
    }

    private static int page_1(Scanner sc, List<Student> students) {
        int status = 0;
        while (status == 0) {
            System.out.println("| Student Name : ");
            sc.nextLine();
            String full_name = sc.nextLine();

            Student new_student = new Student(full_name);
            boolean add_lesson = true;
            while (add_lesson) {
                for (LessonType type : LessonType.values()) {
                    System.out.println("| Lesson " + type.get_description() + " Point : ");
                    double point = sc.nextInt();

                    while (point < 0.0 || point > 100.0) {
                        System.out.println("| Point can be between 0 and 100 : ");
                        point = sc.nextInt();
                    }
                    Lesson lesson = new Lesson(type, point);
                    try {
                        new_student.set_lesson(lesson);
                    }
                    catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }

                add_lesson = false;
            }

            students.add(new_student);
            System.out.println("| Do you wanna add new student? (Yes : 0, No : 1)");
            status = sc.nextInt();
            System.out.println("------------------");
        }

        return 0;
    }

    private static void page_2(List<Student> students) {
        for (Student student : students) {
            System.out.println("| " + student.get_full_name());
            double total = 0;
            for (Lesson lesson : student.get_lessons()) {
                System.out.println("| " + lesson.get_name() + " : " + lesson.get_point());
                total += lesson.get_point();
            }
            System.out.println("| Result : " + (total / 6.0 >= 60 ? "Pass the class." : "Failed the grade."));
            System.out.println("------------------");
        }
    }
}
