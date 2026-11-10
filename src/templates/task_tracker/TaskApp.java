package templates.task_tracker;

import templates.habit_tracker.Habit;

import java.util.Scanner;

public class TaskApp {
    private final Scanner scanner;
    private final TaskManager taskManager;

    public TaskApp() {
        scanner = new Scanner(System.in);
        taskManager = new TaskManager();
    }

    public void start() {
        boolean running = true;

        while (running) {
            showMainMenu();

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    taskManager.createTask(scanner);
                    break;
                case "2":
                    taskManager.showTasks();
                    break;
                case "3":
                    Task task = taskManager.selectTask(scanner);

                    if (task != null) {
                        taskMenu(task);
                    }
                    break;
                case "4":
                    taskManager.deleteTask(scanner);
                    break;
                case "5":
                    running = false;
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Please choose a valid option");
            }
        }

        scanner.close();
    }

    private void showMainMenu() {
        System.out.println();
        System.out.println("MOMENTUM | Task Tracker");
        System.out.println("1. Create task");
        System.out.println("2. Show tasks");
        System.out.println("3. Select task");
        System.out.println("4. Delete task");
        System.out.println("5. Exit");
        System.out.println();
        System.out.print("Choose an option: ");
    }

    private void taskMenu(Task task) {
        boolean selected = true;

        while (selected) {
            System.out.println();
            System.out.println(task.getTaskName().toUpperCase());
            System.out.println("1. Rename task");
            System.out.println("2. Complete task");
            System.out.println("3. Show statistics");
            System.out.println("4. Back");
            System.out.println();
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    task.renameTask(scanner);
                    break;

                case "2":
                    task.complete();
                    break;

                case "3":
                    task.showStatistics();
                    break;

                case "4":
                    selected = false;
                    break;

                default:
                    System.out.println("Please choose a valid option");
            }
        }
    }
}
