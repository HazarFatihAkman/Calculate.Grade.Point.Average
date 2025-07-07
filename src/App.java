
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import models.Lesson;
import models.LessonType;
import models.Student;

public class App {
    public static void main(String[] args) throws Exception {
        List<Student> students = new ArrayList<>();
        List<LessonType> lessons = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        boolean exit = false, lesson_added = false;
        int page, pass_point = -1;

        while (!exit) {
            page = -1;
            while (pass_point < 0 || pass_point > 100) {
                System.out.print("| Enter pass point : ");
                pass_point = sc.nextInt();
            }

            while (!lesson_added) {
                System.err.println("| Add lessons");
                for (LessonType type : LessonType.values()) {
                    System.out.print("| Do you wanna add lesson " + type.get_description() + "? (Y/N) : ");
                    if (Objects.equals(sc.next().toUpperCase(), "Y")) {
                        lessons.add(type);
                    }
                }

                lesson_added = !lessons.isEmpty();
            }


            System.out.println("------------------");
            while (page < 0 || page > 2) {
                System.out.print("| New Student (1) | Student List (2) : ");
                page = sc.nextInt();
            }
            System.out.println("------------------");

            if (page == 1) {
                page_1(sc, students, lessons);
            }
            else if (page == 2) {
                page_2(students, pass_point, lessons.size());
            }

            System.out.print("| Exit (Y/N) : ");
            exit = Objects.equals(sc.next().toUpperCase(), "Y");
        }
        sc.close();
    }

    private static void page_1(Scanner sc, List<Student> students, List<LessonType> lessons) {
        boolean status = false;
        while (!status) {
            System.out.print("| Student Name : ");
            sc.nextLine();
            String full_name = sc.nextLine();

            Student new_student = new Student(full_name);
            boolean add_lesson = true;
            while (add_lesson) {
                for (LessonType type : lessons) {
                    System.out.print("| Lesson " + type.get_description() + " Point : ");
                    double point = sc.nextInt();

                    while (point < 0.0 || point > 100.0) {
                        System.out.print("| Point can be between 0 and 100 : ");
                        point = sc.nextInt();
                    }
                    Lesson lesson = new Lesson(type, point);
                    try {
                        new_student.set_lesson(lesson);
                    }
                    catch (Exception e) {
                        System.err.print(e.getMessage());
                    }
                }

                add_lesson = false;
            }

            students.add(new_student);
            System.out.print("| Do you wanna add new student? (Y/N) : ");
            status = Objects.equals(sc.next().toUpperCase(), "N");
            System.out.println("------------------");
        }
    }

    private static void page_2(List<Student> students, int pass_point, int lesson_count) {
        for (Student student : students) {
            System.out.println("| " + student.get_full_name());
            double total = 0;
            for (Lesson lesson : student.get_lessons()) {
                System.out.println("| " + lesson.get_name() + " : " + lesson.get_point());
                total += lesson.get_point();
            }
            System.out.println("| Result : " + (total / lesson_count >= pass_point ? "Pass the class." : "Failed the grade."));
            System.out.println("------------------");
        }
    }
}
