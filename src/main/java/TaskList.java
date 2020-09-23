import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) throws DukeException{
        this.tasks = tasks;
    }

    /**
     * Deletes a task in the list and update the output file
     */
    public static void deleteEvent(String line) {
        int index = Integer.parseInt(line.substring(7, 8));
        //System.out.println("Index of delete task" + index);
        Task tempTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Noted! I've removed this task:");
        System.out.println("  " + tempTask);
        String out = (tasks.size() > 1) ? ("Now you have " + tasks.size() + " tasks in the list.") :
                "Now you have 1 task in the list.";
        System.out.println(out);
        Storage.updateDataFile();
    }

    /**
     * Adds a to-do to the list of the tasks and update the output file
     *
     * @throws DukeException If there is no description for the list
     */
    public static void addTodo(String line) throws DukeException{

        int i = line.indexOf(' ');
        String taskDescription = (i == -1) ? line : line.substring(i+1);
        if (i == -1) {
            throw new DukeException();
        }
        tasks.add(new Todo(taskDescription));
        printAddNotification();
        Storage.updateDataFile();
    }

    /**
     * Adds a deadline to the list of the tasks and update the output file
     */
    public static void addDeadline(String line) {
        int i1 = line.indexOf(' ');
        int i2 = line.indexOf("/by");
        String taskDescription = line.substring(i1+1, i2 - 1);
        String taskDeadline = line.substring(i2+4);
        tasks.add(new Deadline(taskDescription, taskDeadline));
        printAddNotification();
        Storage.updateDataFile();
    }

    /**
     * Adds an event to the list of the tasks and update the output file
     */
    public static void addEvent(String line) {
        int i1 = line.indexOf(' ');
        int i2 = line.indexOf("/at");
        String taskDescription = line.substring(i1+1, i2 - 1);
        String taskTime = line.substring(i2+4);
        tasks.add(new Event(taskDescription, taskTime));
        printAddNotification();
        Storage.updateDataFile();
    }

    /**
     * Prints the notification and the task added
     */
    public static void printAddNotification() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size()-1));
        String out = (tasks.size() > 1) ? ("Now you have " + tasks.size() + " tasks in the list.") :
                "Now you have 1 task in the list.";
        System.out.println(out);
    }

    /**
     * Marks a task as done and update the output file
     */
    public static void markAsDone(String line) {
        int index = Integer.parseInt(line.substring(5, 6));
        tasks.get(index-1).setStatusDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index-1));
        Storage.updateDataFile();
    }
    /**
     * Prints the list of the tasks with numbering
     */
    public static void printTasks() {
        int index = 1;
        for (Task task : tasks) {
            System.out.print((index++) + ".");
            System.out.println(task);
        }
    }

    public static void findTasks(String line) {
        int i1 = line.indexOf(' ');
        String keyword = line.substring(i1);

        ArrayList<Task> foundTasks;
        foundTasks = (ArrayList<Task>) tasks.stream()
                .filter((s) -> s.getDescription()
                .contains(keyword))
                .collect(Collectors.toList());

        if (foundTasks.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            int index = 1;
            for (Task task : foundTasks) {
                System.out.print((index++) + ".");
                System.out.println(task);
            }
        } else if (foundTasks.size() == 0){
            System.out.println("No match found");
        } else {
            System.out.println("Find error");
        }

    }
}
