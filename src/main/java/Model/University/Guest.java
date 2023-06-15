package Model.University;

public class Guest extends Person {
    Student student;

    public Guest(String name, Role role, Student student) {
        super(name, role);
        this.student = student;
    }

    public Guest(int id, String name, Role role, Student student) {
        super(id, name, role);
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
