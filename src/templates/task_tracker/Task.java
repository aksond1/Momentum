package templates.task_tracker;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public Task(String taskName, TaskStatus status, TaskPriority priority, EffortLevel effortLevel, LocalDate dueDate) {
        this.taskName = taskName;
        this.status = status;
        this.priority = priority;
        this.effortLevel = effortLevel;
        this.dueDate = dueDate;

        this.createdAt = LocalDateTime.now();
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

    public void renameTask(String newTaskName) {
        if (newTaskName == null || newTaskName.isBlank()) {
            throw new IllegalArgumentException("Task name cannot be empty");
        }

        taskName = newTaskName;
    }

    public void changePriority(TaskPriority newPriority) {
        priority = newPriority;
    }

    public void changeEffortLevel(EffortLevel newEffortLevel) {
        effortLevel = newEffortLevel;
    }

    public void updateDescription(String newDescription) {
        description = newDescription;
    }

    public void changeTag(String newTag) {
        tag = newTag;
    }

    public void rescheduleStartDate(LocalDate newStartDate) {
        startDate = newStartDate;
    }

    public void rescheduleDueDate(LocalDate newDueDate) {
        dueDate = newDueDate;
    }

    public void complete() {
        if (status == TaskStatus.DONE) {
            throw new IllegalStateException("Task is already completed");
        }

        status = TaskStatus.DONE;
        completedAt = LocalDateTime.now();
    }
}
