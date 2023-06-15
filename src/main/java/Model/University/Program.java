package Model.University;

public class Program {
    int id;
    String programName;

    public Program(int id, String name) {
        this.id = id;
        this.programName = name;
    }

    public Program(String name) {
        this.programName = name;
    }

    public int getId() {
        return id;
    }

    public String getProgramName() {
        return programName;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", programName='" + programName + '\'' +
                '}';
    }
}
