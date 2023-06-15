package Model.Event;

public class Event {
    int id;
    String name;
    String description;

    public Event(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
