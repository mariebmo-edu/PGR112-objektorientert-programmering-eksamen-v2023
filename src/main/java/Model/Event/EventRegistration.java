package Model.Event;

import Model.University.Person;

public class EventRegistration {

    int id;
    Event event;
    Person person;

    public EventRegistration(int id, Event event, Person person) {
        this.id = id;
        this.event = event;
        this.person = person;
    }

    public EventRegistration(Event event, Person person) {
        this.event = event;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public Person getPerson() {
        return person;
    }
}
