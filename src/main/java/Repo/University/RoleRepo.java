package Repo.University;

import Model.University.Role;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class RoleRepo extends AbstractUniversityRepo<Role>{
    public RoleRepo() throws IOException {
        super("role");
    }

    @Override
    public Role resultMapper(ResultSet resultSet) throws SQLException {
        return new Role(
                resultSet.getInt("id"),
                resultSet.getString("role_name"));
    }

    @Override
    public HashMap<String, Object> modelValues(Role role) {
        HashMap<String, Object> values = new HashMap<>();
        values.put("role_name", role.getRoleName());

        return values;
    }

    @Override
    public int getId(Role role) {
        return role.getId();
    }
}
