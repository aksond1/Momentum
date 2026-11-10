package templates.task_tracker;

import templates.habit_tracker.Habit;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void createTask(Scanner scanner) {
        System.out.print("Write a task name: ");
        String taskName = scanner.nextLine();

        Task task = new Task(taskName);
        tasks.add(task);
    }

    public void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("You don't have any tasks yet.");
            return;
        }

        System.out.println();
        System.out.println("Your Tasks");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            System.out.println((i + 1) + ". " + task.getTaskName()
                    + " | Current status: " + task.getStatus()
            );
        }

        System.out.println();
    }

    public Task selectTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("You don't have any habits yet");
            return null;
        }

        showTasks();

        System.out.print("Choose a task: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice < 1 || choice > tasks.size()) {
                System.out.println("Invalid habit number");
                return null;
            }

            return tasks.get(choice - 1);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
            return null;
        }
    }

    public void deleteTask(Scanner scanner) {
        Task task = selectTask(scanner);

        if (task == null) {
            return;
        }

        System.out.print("Are you sure you want to delete \"" + task.getTaskName() + "\"? (yeah/nah): ");

        String answer = scanner.nextLine();

        if (answer.equals("yeah")) {
            tasks.remove(task);
            System.out.println("Task \"" + task.getTaskName() + "\" was deleted.");
        } else {
            System.out.println("Task was not deleted");
        }
    }

    public void removeStartDate(Scanner scanner) {
        Task task = selectTask(scanner);

        if (task == null) {
            return;
        }

        System.out.print("Write a start date to remove: ");

        try {
            task.removeStartDate();
        } catch (DateTimeParseException e) {
            System.out.println("Please write the correct date.");
        }
    }

    public void removeDueDate(Scanner scanner) {
        Task task = selectTask(scanner);

        if (task == null) {
            return;
        }

        System.out.print("Write a due date to remove: ");

        try {
            task.removeDueDate();
        } catch (DateTimeParseException e) {
            System.out.println("Please write the correct date.");
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
