public class Event extends Task{

    protected String time;

    /**
     * Initializes an instance of Deadline with description and time
     *
     * @param description description of the deadline
     * @param time the time that the event will happen
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: " + this.time + ")");
    }
}

