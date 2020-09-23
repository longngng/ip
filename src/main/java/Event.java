import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event<d4> extends Task{

    protected String time;
    protected LocalDate eventDate;
    //protected LocalTime eventTime = LocalTime.parse("08:30");

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

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: "
                + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
        );
    }
}

