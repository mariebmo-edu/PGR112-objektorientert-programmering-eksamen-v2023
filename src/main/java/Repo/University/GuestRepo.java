package Repo.University;

import Model.University.Guest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GuestRepo extends AbstractUniversityPersonRepo<Guest>{

    RoleRepo roleRepo;

    public GuestRepo(RoleRepo roleRepo) throws IOException {
        super("guest");
        this.roleRepo = roleRepo;
    }

    @Override
    public Guest resultMapper(ResultSet resultSet) throws SQLException {
        return new Guest(
                resultSet.getString("name"),
                roleRepo.getById(resultSet.getInt("role_id"))
        );
    }

    @Override
    public HashMap<String, Object> modelValues(Guest guest) {
        HashMap<String, Object> values = new HashMap<>();
        values.put("name", guest.getName());
        values.put("roleType", guest.getRole().getRoleName());

        return values;
    }

    @Override
    public int getId(Guest guest) {
       return guest.getId();
    }
}
