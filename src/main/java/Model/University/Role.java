package Model.University;

public class Role {
    int id;
    String roleName;

    public Role(int id, String roleType) {
        this.id = id;
        this.roleName = roleType;
    }

    public Role(String roleType) {
        this.roleName = roleType;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
