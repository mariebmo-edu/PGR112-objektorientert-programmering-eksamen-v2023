package Repo.University;

import java.io.IOException;

public abstract class AbstractUniversityPersonRepo<Person> extends AbstractUniversityRepo<Person> {
    public AbstractUniversityPersonRepo(String tableName) throws IOException {
        super(tableName);
    }
}
