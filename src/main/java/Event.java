import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        try {
            this.eventDate = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            System.out.println("The input date is not in standard format. Type help for more info!");
        }
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
        if (eventDate == null) {
            return ("[E]" + super.toString() + " (by: " + time + ")");
        } else {
            return ("[E]" + super.toString() + " (by: "
                    + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        }
    }
}

