import java.time.LocalDateTime;
import java.util.Scanner;

public class Habit {
    private String habitName;
    private LocalDateTime createdAt;
    // completedDates
    // currentStreak
    // longestStreak

    public Habit(String habitName) {
        this.habitName = habitName;
        this.createdAt = LocalDateTime.now();

        System.out.println("--------");
        System.out.println("So, the new habit is: " + habitName);
        System.out.println("Was created: " + createdAt);
    }

    Scanner scanner = new Scanner(System.in);

    public void editHabit() {
        System.out.print("Write new habit name: ");
        habitName = scanner.nextLine();

        System.out.println("Habit was successfully renamed to: " + habitName);
    }

    public void deleteHabit() {
        System.out.println("Habit \"" + habitName + "\" was deleted");
    }
}
