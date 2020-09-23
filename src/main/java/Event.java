import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event<d4> extends Task{

    protected String time;
    protected LocalDate eventDate;
    //protected LocalTime eventTime = LocalTime.parse("08:30");

    /**
     * Initializes an instance of Deadline with description and time
     *
     * @param description description of the deadline
     * @param time the time that the event will happen
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
        this.eventDate = LocalDate.parse(time);
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
        return ("[E]" + super.toString() + " (at: "
                + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
        );
    }
}

