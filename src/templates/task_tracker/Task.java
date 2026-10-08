package templates.task_tracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {

    // main
    private String taskName;
    private String status;
    private String priority;
    private String effortLevel;
    private LocalDate dueDate;

    // additional
    private LocalDateTime createdAt;
    private LocalDate startDate;
    private String description;
    private String tag;

    private List<LocalDate> completedDates = new ArrayList<>();

    public Task(String taskName, String status, String priority, String effortLevel, LocalDate dueDate) {
        this.taskName = taskName;
        this.status = status;
        this.priority = priority;
        this.effortLevel = effortLevel;
        this.dueDate = dueDate;

        this.createdAt = LocalDateTime.now();
    }
}
