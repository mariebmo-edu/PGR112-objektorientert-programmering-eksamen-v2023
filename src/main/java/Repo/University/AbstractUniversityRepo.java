package Repo.University;

import Repo.AbstractRepo;

import java.io.IOException;

public abstract class AbstractUniversityRepo<T> extends AbstractRepo<T> {
    public AbstractUniversityRepo(String tableName) throws IOException {
        super("UNIVERSITY", tableName);
    }
}
