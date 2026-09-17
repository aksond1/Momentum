import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Write a habit name: ");
        Habit habit = new Habit(scan.nextLine());

        habit.renameHabit();
        habit.completeHabit();

        System.out.print("Write a completion date to remove: ");
        habit.removeCompletionDate(LocalDate.parse(scan.nextLine()));
        habit.deleteHabit();
    }
}