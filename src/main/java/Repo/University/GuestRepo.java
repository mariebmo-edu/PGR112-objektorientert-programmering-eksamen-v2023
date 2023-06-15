package Repo.University;

import Model.University.Guest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GuestRepo extends AbstractUniversityPersonRepo<Guest>{

    RoleRepo roleRepo;
    StudentRepo studentRepo;

    public GuestRepo(RoleRepo roleRepo, StudentRepo studentRepo) throws IOException {
        super("guest");
        this.roleRepo = roleRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public Guest resultMapper(ResultSet resultSet) throws SQLException {
        return new Guest(
                resultSet.getString("first_name"),
                roleRepo.getById(resultSet.getInt("role_id")),
                studentRepo.getById(resultSet.getInt("student_id"))
        );
    }

    @Override
    public HashMap<String, Object> modelValues(Guest guest) {
        HashMap<String, Object> values = new HashMap<>();
        values.put("first_name", guest.getName());
        values.put("role_id", guest.getRole().getId());
        values.put("student_id", guest.getStudent().getId());

        return values;
    }

    @Override
    public int getId(Guest guest) {
       return guest.getId();
    }
}
