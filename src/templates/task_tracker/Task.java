package templates.task_tracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public Task(String taskName, TaskPriority priority, EffortLevel effortLevel, LocalDate dueDate) {
        this.taskName = validateTaskName(taskName);
        this.status = TaskStatus.TODO;
        this.priority =  Objects.requireNonNull(priority, "Priority cannot be null");
        this.effortLevel = Objects.requireNonNull(effortLevel, "Effort level cannot be null");
        this.dueDate = dueDate;
        this.createdAt = LocalDateTime.now();
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public EffortLevel getEffortLevel() {
        return effortLevel;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }

    public void renameTask(String newTaskName) {
        taskName = validateTaskName(newTaskName);
    }

    public void changePriority(TaskPriority newPriority) {
        priority = Objects.requireNonNull(newPriority, "Priority cannot be null");
    }

    public void changeEffortLevel(EffortLevel newEffortLevel) {
        effortLevel = Objects.requireNonNull(newEffortLevel, "Effort level cannot be null");
    }

    public void updateDescription(String newDescription) {
        description = newDescription;
    }

    public void changeTag(String newTag) {
        tag = newTag;
    }

    public void rescheduleStartDate(LocalDate newStartDate) {
        validateDates(newStartDate, dueDate);
        startDate = newStartDate;
    }

    public void rescheduleDueDate(LocalDate newDueDate) {
        validateDates(startDate, newDueDate);
        dueDate = newDueDate;
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

    private String validateTaskName(String taskName) {
        if (taskName == null || taskName.isBlank()) {
            throw new IllegalArgumentException("Task name cannot be empty");
        }
        return taskName.trim();
    }

    private void validateDates(LocalDate newStartDate, LocalDate newDueDate) {
        if (newStartDate != null && newDueDate != null && newDueDate.isBefore(newStartDate)) {
            throw new IllegalArgumentException("Due date cannot be before start date");
        }
    }
}
