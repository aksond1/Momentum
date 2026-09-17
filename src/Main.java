import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Write a habit name: ");
        Habit habit = new Habit(scan.nextLine());

        habit.renameHabit();
        habit.completeHabit();
        habit.deleteHabit();
    }
}