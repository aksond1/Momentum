package templates.task_tracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Task {

    // main
    private String taskName;
    private TaskStatus status;
    private TaskPriority priority;
    private EffortLevel effortLevel;
    private LocalDate dueDate;

    // additional
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDate startDate;
    private String description;
    private String tag;

    public Task(String taskName) {
        this.taskName = validateTaskName(taskName);
        this.status = TaskStatus.TODO;
        this.createdAt = LocalDateTime.now();
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setEffortLevel(EffortLevel effortLevel) {
        this.effortLevel = effortLevel;
    }

    public EffortLevel getEffortLevel() {
        return effortLevel;
    }

    public void setDueDate(LocalDate dueDate) {
        validateDates(startDate, dueDate);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setStartDate(LocalDate startDate) {
        validateDates(startDate, dueDate);
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void renameTask(Scanner scanner) {
        System.out.print("Rename the task \"" + taskName + "\" to: ");
        taskName = validateTaskName(scanner.nextLine());

        System.out.println("Habit was successfully renamed to: " + taskName);
    }

    public void removePriority() {
        priority = null;
    }

    public void removeEffortLevel() {
        effortLevel = null;
    }

    public void removeDescription() {
        description = null;
    }

    public void removeTag() {
        tag = null;
    }

    public void removeStartDate() {
        setStartDate(null);
    }

    public void removeDueDate() {
        setDueDate(null);
    }

    public void start() {
        if (status != TaskStatus.TODO) {
            throw new IllegalStateException("Only pending tasks can be started");
        }

        status = TaskStatus.IN_PROGRESS;
    }

    public void reopen() {
        if (status != TaskStatus.DONE) {
            throw new IllegalStateException("Task is not completed");
        }

        status = TaskStatus.TODO;
        completedAt = null;
    }

    public void complete() {
        if (status == TaskStatus.DONE) {
            throw new IllegalStateException("Task is already completed");
        }

        status = TaskStatus.DONE;
        completedAt = LocalDateTime.now();
    }

    public void showStatistics() {
        System.out.println();
        System.out.println("Task: " + taskName);
        System.out.println("Created: " + createdAt);
        System.out.println("Current status: " + status);
        System.out.println();
    }

    private String validateTaskName(String taskName) {
        if (taskName == null || taskName.isBlank()) {
            throw new IllegalArgumentException("Task name cannot be empty");
        }
        return taskName.trim();
    }

    private void validateDates(LocalDate startDate, LocalDate dueDate) {
        if (startDate != null && dueDate != null && dueDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Due date cannot be before start date");
        }
    }
}
