package templates.task_tracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public Task(String taskName, TaskStatus status, TaskPriority priority, EffortLevel effortLevel, LocalDate dueDate) {
        this.taskName = taskName;
        this.status = status;
        this.priority = priority;
        this.effortLevel = effortLevel;
        this.dueDate = dueDate;

        this.createdAt = LocalDateTime.now();
    }

    public void renameTask(String newTaskName) {
        taskName = newTaskName;
    }

    public void complete() {
        if (status == TaskStatus.DONE) {
            throw new IllegalStateException("Task is already completed");
        }

        status = TaskStatus.DONE;
        completedAt = LocalDateTime.now();
    }
}
