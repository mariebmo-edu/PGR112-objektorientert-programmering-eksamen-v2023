package Business;

import Model.Event.*;
import Model.University.*;
import Repo.Event.*;
import Repo.University.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Registration {

    RoleRepo roleRepo;
    ProgramRepo programRepo;

    GuestRepo guestRepo;
    StaffRepo staffRepo;
    StudentRepo studentRepo;

    HashMap<String, EventRegistrationRepo> eventRegistrationRepos = new HashMap<>();
    EventRepo eventRepo;

    ArrayList<Person> people;
    ArrayList<Event> events;
    ArrayList<EventRegistration> eventRegistrations;

    public Registration() throws Exception {
        roleRepo = new RoleRepo();
        programRepo = new ProgramRepo();

        guestRepo = new GuestRepo(roleRepo);
        staffRepo = new StaffRepo(roleRepo);
        studentRepo = new StudentRepo(roleRepo, programRepo);

        eventRepo = new EventRepo();

        eventRegistrationRepos.put("guest", new EventRegistrationRepo(eventRepo, guestRepo));
        eventRegistrationRepos.put("staff", new EventRegistrationRepo(eventRepo, staffRepo));
        eventRegistrationRepos.put("student", new EventRegistrationRepo(eventRepo, studentRepo));


        people = new ArrayList<>();
        events = new ArrayList<>();
        eventRegistrations = new ArrayList<>();

        initialize();
        printAll();
    }

    public void initialize() throws Exception {
        people.addAll(guestRepo.getAll());
        people.addAll(staffRepo.getAll());
        people.addAll(studentRepo.getAll());

        events.addAll(eventRepo.getAll());
        eventRegistrations.addAll(eventRegistrationRepos.get("guest").getAll());
        eventRegistrations.addAll(eventRegistrationRepos.get("staff").getAll());
        eventRegistrations.addAll(eventRegistrationRepos.get("student").getAll());
    }

    public void printAll() {
        System.out.println("People:");
        for (Person person : people) {
            System.out.println(person);
        }

        System.out.println("Events:");
        for (Event event : events) {
            System.out.println(event);
        }

        System.out.println("Event Registrations:");
        for (EventRegistration eventRegistration : eventRegistrations) {
            System.out.println(eventRegistration);
        }
    }
}
