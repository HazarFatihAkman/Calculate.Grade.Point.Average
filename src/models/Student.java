package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student {
    private UUID id;
    private String full_name;
    private List<Lesson> lessons;

    public Student(String full_name) throws NullPointerException {
        id = UUID.randomUUID();
        if (full_name == null) {
            throw new NullPointerException("Full name cannot be null.");
        }

        this.full_name = full_name;
        lessons = new ArrayList<>();
    }

    public UUID get_id() {
        return id;
    }

    public String get_full_name() {
        return full_name;
    }

    public List<Lesson> get_lessons() {
        return lessons;
    }

    public void set_lesson(Lesson lesson) throws NullPointerException, Exception {
        if (lesson == null) {
            throw new NullPointerException("Lesson cannot be null.");
        }

        if (!lessons.stream().anyMatch(i -> i.get_type() == lesson.get_type())) {
            lessons.add(lesson);
        }
        else {
            throw new Exception("Lesson is already exists. Update point!");
        }
    }
}
