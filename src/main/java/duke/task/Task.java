package duke.task;
public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numOfTask = 0;
    public final String TICK = "\u2713";
    public final String CROSS = "\u2718";
    /**
     * Initializes an instance of Task with description
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {
        this.description = "Default";
        this.isDone = false;
    }

    /**
     * Returns the tick or cross symbols based on whether the task is done
     *
     * @return the corresponding icon
     */
    public String getStatusIcon() {
        return (isDone ? TICK : CROSS); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatusDone() {
        isDone = true;
    }
    public int getDoneStatus () {
        return isDone? 1 : 0;
    }

    /**
     * Returns the formatted string of the instance to print
     *
     * @return the formatted string
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description );
    }
}