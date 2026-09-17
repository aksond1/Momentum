import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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

        System.out.println("The new habit \"" + habitName + "\" was created: "+ createdAt);
    }

    Scanner scanner = new Scanner(System.in);

    public void renameHabit() {
        System.out.print("Rename the habit \"" + habitName + "\" to: ");
        habitName = scanner.nextLine();

        System.out.println("Habit was successfully renamed to: " + habitName);
    }

    public void completeHabit() {

        System.out.print("Did you do this habit earlier? (yeah/nah): ");
        String habitCompletedEarlier = scanner.nextLine();

        if (habitCompletedEarlier.equals("yeah")) {
            while (true) {
                System.out.print("Please write a date when you completed this habit (yyyy-mm-dd | press q to quit): ");
                String habitCompletedDate = scanner.nextLine();

                if (habitCompletedDate.equals("q")) {
                    break;
                }

                try {
                    LocalDate date = LocalDate.parse(habitCompletedDate);

                    if (completedDates.contains(date)) {
                        System.out.println("You've already completed this habit on that day");
                        continue;
                    }

                    completedDates.add(date);
                    calculateStreaks();

                } catch (DateTimeParseException e) {
                    System.out.println("Please write the correct date");
                }
            }
        } else if (habitCompletedEarlier.equals("nah")) {
            System.out.println("What a loser");
        } else {
            System.out.println("Please learn how to write properly");
        }

        System.out.println("Your current streak is: " + currentStreak);
        System.out.println("Your longest streak is: " + longestStreak);
        System.out.println("Completed dates: " + completedDates);
    }

    public void calculateStreaks() {
        if (completedDates.isEmpty()) {
            currentStreak = 0;
            longestStreak = 0;
            return;
        }

        int streak = 1;
        longestStreak = 1;

        completedDates.sort(LocalDate::compareTo);

        for (int i = 1; i < completedDates.size(); i++) {
            LocalDate previousDate = completedDates.get(i - 1);
            LocalDate currentDate = completedDates.get(i);

            if (currentDate.equals(previousDate.plusDays(1))) {
                streak++;
            } else {
                longestStreak = Math.max(longestStreak, streak);
                streak = 1;
            }
        }

        longestStreak = Math.max(longestStreak, streak);

        LocalDate today = LocalDate.now();
        currentStreak = 0;

        if (completedDates.contains(today)) {
            currentStreak = 1;

            LocalDate date = today.minusDays(1);

            while (completedDates.contains(date)) {
                currentStreak++;
                date = date.minusDays(1);
            }
        }
    }

    public void removeCompletionDate(LocalDate date) {
        if (completedDates.remove(date)) {
            calculateStreaks();
            System.out.println("Completion date removed");

            System.out.println("Your current streak is: " + currentStreak);
            System.out.println("Your longest streak is: " + longestStreak);
            System.out.println("Completed dates: " + completedDates);
        } else {
            System.out.println("You didn't complete this habit on that day");
        }
    }

    public void deleteHabit() {
        System.out.println("Habit \"" + habitName + "\" was deleted");
    }
}
