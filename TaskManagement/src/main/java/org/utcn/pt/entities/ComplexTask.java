package org.utcn.pt.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class ComplexTask extends Task implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Task> subTasks;

    public ComplexTask(int task_id, String name) {
        super(task_id, name);
        this.subTasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        subTasks.add(task);
    }
    public List<Task> getSubTasks() {
        return subTasks;
    }
    public void deleteTask(Task task) {
        subTasks.remove(task);
    }

    @Override
    public int estimateDuration() {
        int duration = 0;
        for (Task task : subTasks) {
            duration += task.estimateDuration();
        }
        return duration;
    }

    @Override
    public String getStatus() {
        if (subTasks == null || subTasks.isEmpty()) {
            return super.getStatus();
        }
        for (Task task : subTasks) {
            if ("Uncompleted".equals(task.getStatus())) {
                return "Uncompleted";
            }
        }
        return "Completed";
    }

    @Override
    public void setStatus(String status) {
        super.setStatus(status);
        if (subTasks != null) {
            for (Task task : subTasks) {
                task.setStatus(status);
            }
        }
    }
}

