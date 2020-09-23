import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected String by;
    protected LocalDate deadlineDate;

    /**
     * Initializes an instance of Deadline with description and time
     *
     * @param description description of the deadline
     * @param by the deadline time of the event
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.deadlineDate = LocalDate.parse(by);
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: "
                + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}
