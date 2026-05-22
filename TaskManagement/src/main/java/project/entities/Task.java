package project.entities;

import java.io.Serial;
import java.io.Serializable;

public abstract sealed class Task implements Serializable permits SimpleTask, ComplexTask {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int taskId;
    private String name;
    private String status;

    public Task(int task_id, String name) {
        this.taskId = task_id;
        this.name = name;
        this.status = "Uncompleted";
    }

    public int getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if ("Completed".equals(status) || "Uncompleted".equals(status)) {
            this.status = status;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int estimateDuration();
}

