import java.util.ArrayList;
import java.util.Scanner;

class Event {
    String title;
    String date;
    String description;

    public Event(String title, String date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nDate: " + date + "\nDescription: " + description + "\n";
    }
}

public class EventManagementSystem {
    private static ArrayList<Event> events = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Event");
            System.out.println("2. View Events");
            System.out.println("3. Delete Event");
            System.out.println("4. Search Event");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    viewEvents();
                    break;
                case 3:
                    deleteEvent();
                    break;
                case 4:
                    searchEvent();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void addEvent() {
        System.out.print("Enter event title: ");
        String title = scanner.nextLine();
        System.out.print("Enter event date: ");
        String date = scanner.nextLine();
        System.out.print("Enter event description: ");
        String description = scanner.nextLine();

        Event event = new Event(title, date, description);
        events.add(event);
        System.out.println("Event added successfully!\n");
    }

    private static void viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.\n");
        } else {
            for (Event event : events) {
                System.out.println(event);
            }
        }
    }

    private static void deleteEvent() {
        System.out.print("Enter the title of the event to delete: ");
        String title = scanner.nextLine();

        Event eventToRemove = null;
        for (Event event : events) {
            if (event.title.equalsIgnoreCase(title)) {
                eventToRemove = event;
                break;
            }
        }

        if (eventToRemove != null) {
            events.remove(eventToRemove);
            System.out.println("Event deleted successfully!\n");
        } else {
            System.out.println("Event not found.\n");
        }
    }

    private static void searchEvent() {
        System.out.print("Enter the title of the event to search: ");
        String title = scanner.nextLine();

        for (Event event : events) {
            if (event.title.equalsIgnoreCase(title)) {
                System.out.println(event);
                return;
            }
        }

        System.out.println("Event not found.\n");
    }
}
