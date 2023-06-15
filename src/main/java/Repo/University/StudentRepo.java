package Repo.University;

import Model.University.Student;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class StudentRepo extends AbstractUniversityPersonRepo<Student>{

    RoleRepo roleRepo;
    ProgramRepo programRepo;

    public StudentRepo(RoleRepo roleRepo, ProgramRepo programRepo) throws IOException {
        super("student");
        this.roleRepo = roleRepo;
        this.programRepo = programRepo;
    }

    @Override
    public Student resultMapper(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                roleRepo.getById(resultSet.getInt("role_id")),
                programRepo.getById(resultSet.getInt("program_id"))
        );
    }

    @Override
    public HashMap<String, Object> modelValues(Student student) {
        HashMap<String, Object> values = new HashMap<>();
        values.put("name", student.getName());
        values.put("role_name", student.getRole().getRoleName());
        values.put("program_name", student.getProgram().getProgramName());

        return values;
    }

    @Override
    public int getId(Student student) {
        return student.getId();
    }
}
