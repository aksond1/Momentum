import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Habit {
    private String habitName;
    private LocalDateTime createdAt;

    private List<LocalDate> completedDates = new ArrayList<>();
    private int currentStreak;
    private int longestStreak;

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

    public void completeHabit() {
        System.out.print("Did you do this habit today? (yeah/nah): ");
        String habitComplete = scanner.nextLine();

        if (habitComplete.equals("yeah")) {
            completedDates.add(LocalDate.now());
            calculateCurrentStreak();

            System.out.println("I'm proud of you buddy. You're a real legend today, keep it up!");
        } else if (habitComplete.equals("nah")) {
            System.out.println("You're kinda weak idk");
        } else {
            System.out.println("Please learn how to write properly");
        }

        System.out.print("Did you do this habit earlier? (yeah/nah): ");
        String habitCompletedEarlier = scanner.nextLine();

        if (habitCompletedEarlier.equals("yeah")) {
            while (true) {
                System.out.print("Please write a date when you completed this habit (yyyy-mm-dd | press q to quit): ");
                String habitCompletedDate = scanner.nextLine();

                if (habitCompletedDate.equals("q")) {
                    break;
                } else {
                    completedDates.add(LocalDate.parse(habitCompletedDate));
                    calculateCurrentStreak();
                }
            }
        } else if (habitCompletedEarlier.equals("nah")) {
            System.out.println("What a loser");
        } else {
            System.out.println("Please learn how to write properly");
        }

        System.out.println("Your current streak is: " + currentStreak);
        System.out.println("Completed dates: " + completedDates);
    }

    public void calculateCurrentStreak() {
        if (completedDates.isEmpty()) {
            return;
        }

        currentStreak = 1;

        completedDates.sort(LocalDate::compareTo);

        for (int i = 1; i < completedDates.size(); i++) {
            LocalDate previousDate = completedDates.get(i - 1);
            LocalDate currentDate = completedDates.get(i);

            if (currentDate.equals(previousDate.plusDays(1))) {
                currentStreak++;
            } else {
                currentStreak = 1;
            }
        }
    }

    public void deleteHabit() {
        System.out.println("Habit \"" + habitName + "\" was deleted");
    }
}
