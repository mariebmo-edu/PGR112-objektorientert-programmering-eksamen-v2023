package Business;

import Model.Event.*;
import Model.University.*;
import Repo.Event.*;
import Repo.University.*;
import View.UiHandler;

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

        people = new ArrayList<>();
        events = new ArrayList<>();
        eventRegistrations = new ArrayList<>();

        initialize();
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

    public void logIn(Scanner scanner){
        System.out.println("Please log in to continue.");

        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        //check if user exists in people list, and is a student
        for (Person person : people) {
            if (person.getName().equals(name) && person instanceof Student) {
                user = (Student) person;
                break;
            }
        }

        if (user == null) {
            System.out.println("User not found.");
        }
    }

    public void printMenu(){
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

    public void handleMenu(Scanner scanner){

        String input = "";
        while(!input.equalsIgnoreCase("Q")){
            printMenu();
            input = scanner.nextLine();
            switch(input){
                case "1":
                    attendEvent(scanner);
                    break;
                    /*
                case "2":
                    inviteGuest(scanner);
                    break;
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

    public void attendEvent(Scanner scanner){
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

        UiHandler.PrintMenuList(validEvents.stream().map(Event::getName).collect(Collectors.toCollection(ArrayList::new)));

        String eventIndex = "";
        Event selectedEvent= null;

        while (!eventIndex.equalsIgnoreCase("Q") || selectedEvent == null){
            eventIndex = scanner.nextLine();
            selectedEvent = events.get(Integer.parseInt(eventIndex) - 1);
        }

        EventRegistration eventRegistration = new EventRegistration(selectedEvent, user);
        eventRegistrationRepos.get("student").insert(eventRegistration);
        eventRegistrations.add(eventRegistration);
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
