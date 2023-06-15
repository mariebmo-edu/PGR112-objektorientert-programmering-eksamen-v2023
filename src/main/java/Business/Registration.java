package Business;

import Model.Event.*;
import Model.University.*;
import Repo.Event.*;
import Repo.University.*;
import View.UiHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Registration {

    RoleRepo roleRepo;
    ProgramRepo programRepo;

    StaffRepo staffRepo;
    StudentRepo studentRepo;
    GuestRepo guestRepo;

    HashMap<String, EventRegistrationRepo> eventRegistrationRepos = new HashMap<>();
    EventRepo eventRepo;

    ArrayList<Program> programs;
    ArrayList<Role> roles;
    ArrayList<Person> people;
    ArrayList<Event> events;
    ArrayList<EventRegistration> eventRegistrations;

    Student user;

    public Registration() throws Exception {
        roleRepo = new RoleRepo();
        programRepo = new ProgramRepo();

        staffRepo = new StaffRepo(roleRepo);
        studentRepo = new StudentRepo(roleRepo, programRepo);
        guestRepo = new GuestRepo(roleRepo, studentRepo);

        eventRepo = new EventRepo();

        eventRegistrationRepos.put("guest", new EventRegistrationRepo(eventRepo, guestRepo));
        eventRegistrationRepos.put("staff", new EventRegistrationRepo(eventRepo, staffRepo));
        eventRegistrationRepos.put("student", new EventRegistrationRepo(eventRepo, studentRepo));

        programs = new ArrayList<>();
        roles = new ArrayList<>();
        people = new ArrayList<>();
        events = new ArrayList<>();
        eventRegistrations = new ArrayList<>();

        initialize();
    }

    private void initialize() throws Exception {
        people.addAll(guestRepo.getAll());
        people.addAll(staffRepo.getAll());
        people.addAll(studentRepo.getAll());

        events.addAll(eventRepo.getAll());

        eventRegistrations.addAll(eventRegistrationRepos.get("guest").getAll());
        eventRegistrations.addAll(eventRegistrationRepos.get("staff").getAll());
        eventRegistrations.addAll(eventRegistrationRepos.get("student").getAll());

        roles.addAll(roleRepo.getAll());
        programs.addAll(programRepo.getAll());
    }

    private void logIn(Scanner scanner){
        System.out.println("Please log in to continue.");

        System.out.println("Enter your name:");
        String name = "";

        while(user == null){
            name = scanner.nextLine();

            //check if user exists in people list, and is a student
            for (Person person : people) {
                if (person.getName().equalsIgnoreCase(name) && person instanceof Student) {
                    user = (Student) person;
                    break;
                }
            }

            if (user == null) {
                System.out.println("User not found.");
            }
        }
    }

    private void printMenu(){
        System.out.println("1. Attend an event");
        System.out.println("2. Invite guests");
        System.out.println("3. Unregister from an event");
        System.out.println("4. Print Event Registrations");
        System.out.println("5. Print Registered Students");
        System.out.println("6. Print Registered Students from a Program");
        System.out.println("7. Search for a Person");
        System.out.println("8. Print Program");
        System.out.println("Q. Quit");
    }

    private void handleMenu(Scanner scanner){

        String input = "";
        while(!input.equalsIgnoreCase("Q")){
            printMenu();
            input = scanner.nextLine();
            switch(input){
                case "1":
                    attendEvent(scanner);
                    break;

                case "2":
                    inviteGuest(scanner);
                    break;
                    /*
                case "3":
                    unregisterEvent(scanner);
                    break;
                case "4":
                    printEventRegistrations();
                    break;
                case "5":
                    printRegisteredStudents();
                    break;
                case "6":
                    printRegisteredStudentsFromProgram(scanner);
                    break;
                case "7":
                    searchForPerson(scanner);
                    break;
                case "8":
                    printProgram(scanner);
                    break;

                     */
                case "Q":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }

    public void start(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Event Registration System!");
        if(user == null){
            logIn(scanner);
        }

        if(user != null){
            System.out.println("Welcome, " + user.getName() + "!");
            handleMenu(scanner);
        }
    }

    private ArrayList<String> getEventNames(ArrayList<Event> events){
        return events.stream().map(Event::getName).collect(Collectors.toCollection(ArrayList::new));
    }

    private void attendEvent(Scanner scanner){
        System.out.println("Enter the name of the event you would like to attend:");
        //only show events that user is not already registered for
        ArrayList<Event> validEvents = new ArrayList<>();

        for(Event event : events){
            boolean alreadyRegistered = false;
            for(EventRegistration eventRegistration : eventRegistrations){
                if(eventRegistration.getEvent().equals(event) && eventRegistration.getPerson().equals(user)){
                    alreadyRegistered = true;
                    break;
                }
            }
            if(!alreadyRegistered){
                validEvents.add(event);
            }
        }
        UiHandler.PrintMenuList(getEventNames(validEvents));

        String eventIndex = "";
        Event selectedEvent= null;

        while (!eventIndex.equalsIgnoreCase("Q") && selectedEvent == null){
            eventIndex = scanner.nextLine();
            selectedEvent = events.get(Integer.parseInt(eventIndex) - 1);

            if(selectedEvent == null){
                System.out.println("Invalid input.");
            }
        }

        EventRegistration eventRegistration = new EventRegistration(selectedEvent, user);
        eventRegistrationRepos.get("student").insert(eventRegistration);
        eventRegistrations.add(eventRegistration);
    }


    private void inviteGuest(Scanner scanner){
        System.out.println("Which event would you like to invite guests to?");
        //only show events that user is registered for
        ArrayList<Event> validEvents = new ArrayList<>();

        for(Event event : events){
            boolean alreadyRegistered = false;
            for(EventRegistration eventRegistration : eventRegistrations){
                if(eventRegistration.getEvent().equals(event) && eventRegistration.getPerson().equals(user)){
                    alreadyRegistered = true;
                    break;
                }
            }
            if(alreadyRegistered){
                validEvents.add(event);
            }
        }

        UiHandler.PrintMenuList(getEventNames(validEvents));

        String eventIndex = "";
        Event selectedEvent= null;

        while (!eventIndex.equalsIgnoreCase("Q") && selectedEvent == null){
            eventIndex = scanner.nextLine();
            selectedEvent = events.get(Integer.parseInt(eventIndex) - 1);

            if(selectedEvent == null){
                System.out.println("Invalid input.");
            }
        }

        System.out.println("Enter the name of the guest you would like to invite:");
        String guestName = scanner.nextLine();

        // check if guest exists in people list, and is a guest (not a student or staff)
        Guest guest = null;

        for (Person person : people) {
            if (person.getName().equals(guestName) && person instanceof Guest) {
                guest = (Guest) person;
                break;
            }
        }

        // if guest exists, see if they are already registered for the event, if not, register them
        if (guest != null) {
            boolean alreadyRegistered = false;
            for(EventRegistration eventRegistration : eventRegistrations){
                if(eventRegistration.getEvent().equals(selectedEvent) && eventRegistration.getPerson().equals(guest)){
                    alreadyRegistered = true;
                    System.out.println("Guest already registered for this event.");
                    break;
                }
            }
            if(!alreadyRegistered){
                EventRegistration eventRegistration = new EventRegistration(selectedEvent, guest);
                eventRegistrationRepos.get("guest").insert(eventRegistration);
                eventRegistrations.add(eventRegistration);
            }
        } else {
            Role guestRole = roles.stream().filter(role -> role.getRoleName().equalsIgnoreCase("Guest")).findFirst().orElse(null);
            if(guestRole == null){
                guestRole = new Role("Guest");
                roleRepo.insert(guestRole);
                roles.add(guestRole);
            }

            guest = new Guest(guestName, guestRole, user);
            guestRepo.insert(guest);
            people.add(guest);

            EventRegistration eventRegistration = new EventRegistration(selectedEvent, guest);
            eventRegistrationRepos.get("guest").insert(eventRegistration);
            eventRegistrations.add(eventRegistration);
        }

    }



    private void printAll() {
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
