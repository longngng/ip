package duke.task;

import duke.task.Task;

public class Event extends Task {

    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: " + this.time + ")");
    }
}

