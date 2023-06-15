package Repo.Event;

import Model.Event.EventRegistration;
import Model.University.Person;
import Repo.University.AbstractUniversityPersonRepo;
import Repo.University.AbstractUniversityRepo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class EventRegistrationRepo extends AbstractEventRepo<EventRegistration>{

    EventRepo eventRepo;
    AbstractUniversityPersonRepo<? extends Person> universityPersonRepo;

    public EventRegistrationRepo(EventRepo eventRepo, AbstractUniversityPersonRepo<? extends Person> universityPersonRepo) throws IOException {
        super("event_registration");
        this.eventRepo = eventRepo;
        this.universityPersonRepo = universityPersonRepo;
    }

    @Override
    public EventRegistration resultMapper(ResultSet resultSet) throws SQLException {
        return new EventRegistration(
                resultSet.getInt("id"),
                eventRepo.getById(resultSet.getInt("event_id")),
                universityPersonRepo.getById(resultSet.getInt("attendee_id"))
        );
    }

    @Override
    public HashMap<String, Object> modelValues(EventRegistration eventRegistration) {
        HashMap<String, Object> values = new HashMap<>();
        values.put("event_id", eventRegistration.getEvent().getId());
        values.put("attendee_id", eventRegistration.getPerson().getId());
        values.put("attendee_role_id", eventRegistration.getPerson().getRole().getId());

        return values;
    }

    @Override
    public int getId(EventRegistration eventRegistration) {
        return eventRegistration.getId();
    }
}
