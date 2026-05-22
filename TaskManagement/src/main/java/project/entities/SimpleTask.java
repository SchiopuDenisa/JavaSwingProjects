package project.entities;

import java.io.Serial;
import java.io.Serializable;

import static java.lang.Math.abs;

public final class SimpleTask extends Task implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int startHour;
    private int endHour;

    public SimpleTask(int task_id, String name, int startHour, int endHour) {
        super(task_id, name);

        if (startHour < 0 || startHour > 23 || endHour < 0 || endHour > 23) {
            throw new IllegalArgumentException("Hours must be between 0 and 23.");
        }
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Override
    public int estimateDuration() {
        return abs(endHour - startHour);
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
}

