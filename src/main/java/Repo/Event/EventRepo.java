package Repo.Event;

import Model.Event.Event;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class EventRepo extends AbstractEventRepo<Event>{
    public EventRepo() throws IOException {
        super("event");
    }

    @Override
    public Event resultMapper(ResultSet resultSet) throws SQLException {
        return new Event(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description")
        );
    }

    @Override
    public HashMap<String, Object> modelValues(Event event) {
        HashMap<String, Object> values = new HashMap<>();
        values.put("name", event.getName());
        values.put("description", event.getDescription());

        return values;
    }

    @Override
    public int getId(Event event) {
        return event.getId();
    }
}
