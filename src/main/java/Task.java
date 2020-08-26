public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numOfTask = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task() {
        this.description = "Default";
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
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

}