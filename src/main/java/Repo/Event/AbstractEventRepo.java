package Repo.Event;

import Repo.AbstractRepo;

import java.io.IOException;

public abstract class AbstractEventRepo<T> extends AbstractRepo<T> {
    public AbstractEventRepo(String tableName) throws IOException {
        super("EVENT", tableName);
    }
}
