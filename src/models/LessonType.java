package models;

public enum LessonType {
    MATH(0, "Mathematic"),
    PHYSICS(1, "Physics"),
    CHEMISTRY(2, "Chemistry"),
    TURKISH(3, "Turkish"),
    HISTORY(4, "History"),
    MUSIC(5, "Music");

    private int code;
    private String description;

    LessonType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static LessonType from_int(int code) {
        for (LessonType type : LessonType.values()) {
            if (type.code == code) {
                return  type;
            }
        }
        throw new IllegalArgumentException("Invalid code :" + code);
    }

    public String get_description() {
        return description;
    }
}
