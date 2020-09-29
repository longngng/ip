package duke.task;

import duke.task.Task;

public class Todo extends Task {

    /**
     * Initializes an instance of To-do with description and time
     *
     * @param description description of the to-do
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
