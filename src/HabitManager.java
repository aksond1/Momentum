import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HabitManager {
    private List<Habit> habits = new ArrayList<>();

    public void createHabit(Scanner scanner) {
        System.out.print("Write a habit name: ");
        String habitName = scanner.nextLine();

        Habit habit = new Habit(habitName);
        habits.add(habit);
    }

    public void showHabits() {
        if (habits.isEmpty()) {
            System.out.println("You don't have any habits yet.");
            return;
        }

        System.out.println();
        System.out.println("Your Habits");

        for (int i = 0; i < habits.size(); i++) {
            Habit habit = habits.get(i);

            System.out.println((i + 1) + ". " + habit.getHabitName()
                    + " | Current streak: " + habit.getCurrentStreak()
                    + " | Longest streak: " + habit.getLongestStreak()
            );
        }

        System.out.println();
    }

    public Habit selectHabit(Scanner scanner) {
        if (habits.isEmpty()) {
            System.out.println("You don't have any habits yet");
            return null;
        }

        showHabits();

        System.out.print("Choose a habit: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice < 1 || choice > habits.size()) {
                System.out.println("Invalid habit number");
                return null;
            }

            return habits.get(choice - 1);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
            return null;
        }
    }

    public void deleteHabit(Scanner scanner) {
        Habit habit = selectHabit(scanner);

        if (habit == null) {
            return;
        }

        System.out.print("Are you sure you want to delete \"" + habit.getHabitName() + "\"? (yeah/nah): ");

        String answer = scanner.nextLine();

        if (answer.equals("yeah")) {
            habits.remove(habit);
            System.out.println("Habit \"" + habit.getHabitName() + "\" was deleted.");
        } else {
            System.out.println("Habit was not deleted");
        }
    }

    public void removeCompletionDate(Scanner scanner) {
        Habit habit = selectHabit(scanner);

        if (habit == null) {
            return;
        }

        System.out.print("Write a completion date to remove: ");

        try {
            LocalDate date = LocalDate.parse(scanner.nextLine());
            habit.removeCompletionDate(date);

        } catch (DateTimeParseException e) {
            System.out.println("Please write the correct date.");
        }
    }

    public boolean isEmpty() {
        return habits.isEmpty();
    }

    public List<Habit> getHabits() {
        return habits;
    }
}
