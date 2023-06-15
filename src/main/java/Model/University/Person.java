package Model.University;

public abstract class Person {
    private int id;
    private String name;
    private Role role;

    public Person(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public Person(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }
}
