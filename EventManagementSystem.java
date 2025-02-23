import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
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
            System.out.println("\nEvent Management System");
            System.out.println("1. Add Event");
            System.out.println("2. View Events");
            System.out.println("3. Delete Event");
            System.out.println("4. Search Event");
            System.out.println("5. Update Event");
            System.out.println("6. Sort Events by Date");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = getValidChoice();

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
                    updateEvent();
                    break;
                case 6:
                    sortEventsByDate();
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static int getValidChoice() {
        int choice = -1;
        while (choice < 1 || choice > 7) {
            try {
                choice = scanner.nextInt();
                if (choice < 1 || choice > 7) {
                    System.out.print("Invalid choice. Please choose a number between 1 and 7: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); // Consume invalid input
            }
        }
        scanner.nextLine(); // Consume newline
        return choice;
    }

    private static void addEvent() {
        System.out.print("Enter event title: ");
        String title = scanner.nextLine();
        System.out.print("Enter event date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter event description: ");
        String description = scanner.nextLine();

        if (title.isEmpty() || date.isEmpty() || description.isEmpty()) {
            System.out.println("All fields are required. Event not added.\n");
        } else {
            Event event = new Event(title, date, description);
            events.add(event);
            System.out.println("Event added successfully!\n");
        }
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

    private static void updateEvent() {
        System.out.print("Enter the title of the event to update: ");
        String title = scanner.nextLine();

        for (Event event : events) {
            if (event.title.equalsIgnoreCase(title)) {
                System.out.print("Enter new event date (YYYY-MM-DD): ");
                String newDate = scanner.nextLine();
                System.out.print("Enter new event description: ");
                String newDescription = scanner.nextLine();

                if (!newDate.isEmpty()) {
                    event.date = newDate;
                }
                if (!newDescription.isEmpty()) {
                    event.description = newDescription;
                }

                System.out.println("Event updated successfully!\n");
                return;
            }
        }

        System.out.println("Event not found.\n");
    }

    private static void sortEventsByDate() {
        if (events.isEmpty()) {
            System.out.println("No events available to sort.\n");
        } else {
            Collections.sort(events, new Comparator<Event>() {
                @Override
                public int compare(Event e1, Event e2) {
                    return e1.date.compareTo(e2.date);
                }
            });
            System.out.println("Events sorted by date successfully!\n");
            viewEvents();
        }
    }
}
