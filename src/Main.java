import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HabitManager habitManager = new HabitManager();

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("MOMENTUM | Habit Tracker");
            System.out.println("1. Create habit");
            System.out.println("2. Show habits");
            System.out.println("3. Select habit");
            System.out.println("4. Delete habit");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    habitManager.createHabit(scanner);
                    break;

                case "2":
                    habitManager.showHabits();
                    break;

                case "3":
                    Habit habit = habitManager.selectHabit(scanner);

                    if (habit != null) {
                        habitMenu(scanner, habit);
                    }

                    break;

                case "4":
                    habitManager.deleteHabit(scanner);
                    break;

                case "5":
                    running = false;
                    System.out.println("Bye");
                    break;

                default:
                    System.out.println("Plz choose a valid option");
            }
        }

        scanner.close();
    }

    public static void habitMenu(Scanner scanner, Habit habit) {
        boolean selected = true;

        while (selected) {
            System.out.println();
            System.out.println(habit.getHabitName().toUpperCase());
            System.out.println("1. Rename habit");
            System.out.println("2. Complete habit");
            System.out.println("3. Remove completion date");
            System.out.println("4. Show statistics");
            System.out.println("5. Back");
            System.out.println();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    habit.renameHabit(scanner);
                    break;
                case "2":
                    habit.completeHabit(scanner);
                    break;
                case "3":
                    System.out.print("Write a completion date to remove: ");

                    try {
                        LocalDate date = LocalDate.parse(scanner.nextLine());
                        habit.removeCompletionDate(date);
                    } catch (DateTimeParseException e) {
                        System.out.println("Plz write the correct date");
                    }
                    break;
                case "4":
                    habit.showStatistics();
                    break;
                case "5":
                    selected = false;
                    break;
                default:
                    System.out.println("Please choose a valid option");
            }
        }
    }
}