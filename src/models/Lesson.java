package models;

public class Lesson {
    private String name;
    private double point;
    private LessonType type;

    public Lesson(LessonType type, double point) throws NullPointerException {
        if (type == null) {
            throw new NullPointerException("Type cannot be null.");
        }

        this.type = type;
        this.point = point;
        name = type.get_description();
    }

    public void set_point(double point) {
        this.point = point;
    }

    public String get_name() {
        return name;
    }

    public double get_point() {
        return point;
    }

    public LessonType get_type() {
        return type;
    }
}