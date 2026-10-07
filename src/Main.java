import templates.habit_tracker.HabitApp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        User user = new User("aksond1");
        System.out.println("Welcome, " + user.getUsername());

        System.out.print("Choose an option to continue (1 - habit tracker, ... ): ");
        int userInput = Integer.parseInt(scan.nextLine());

        switch (userInput) {
            case 1 -> new HabitApp().start();
            // there will be more cases for each template
            default -> System.out.println("Please write a correct input");
        }
    }
}