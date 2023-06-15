package Model.University;

public class Student extends Person {

    private Program program;

    public Student(String name, Role role, Program program) {
        super(name, role);

        this.program = program;
    }

    public Student(int id, String name, Role role, Program program) {
        super(id, name, role);

        this.program = program;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgramName(Program program) {
        this.program = program;
    }
}
