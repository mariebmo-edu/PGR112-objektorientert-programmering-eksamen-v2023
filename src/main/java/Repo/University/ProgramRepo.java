package Repo.University;

import Model.University.Program;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ProgramRepo extends AbstractUniversityRepo<Program> {
    public ProgramRepo() throws IOException {
        super("program");
    }

    @Override
    public Program resultMapper(ResultSet resultSet) throws SQLException {
        return new Program(
                resultSet.getInt("id"),
                resultSet.getString("program_name"));
    }

    @Override
    public HashMap<String, Object> modelValues(Program program) {
        HashMap<String, Object> values = new HashMap<>();
        values.put("program_name", program.getProgramName());

        return values;
    }

    @Override
    public int getId(Program program) {
        return program.getId();
    }
}
