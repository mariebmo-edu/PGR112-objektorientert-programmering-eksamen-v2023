package Repo.University;

import Model.University.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class StaffRepo extends AbstractUniversityPersonRepo<Staff>{

    RoleRepo roleRepo;

    public StaffRepo(RoleRepo roleRepo) throws Exception {
        super("staff");
        this.roleRepo = roleRepo;
    }


    @Override
    public Staff resultMapper(ResultSet resultSet) throws SQLException {
        return new Staff(
                resultSet.getString("name"),
                roleRepo.getById(resultSet.getInt("role_id"))
        );
    }

    @Override
    public HashMap<String, Object> modelValues(Staff staff) {
        HashMap<String, Object> values = new HashMap<>();
        values.put("name", staff.getName());
        values.put("roleType", staff.getRole().getRoleName());

        return values;
    }

    @Override
    public int getId(Staff staff) {
        return staff.getId();
    }
}
