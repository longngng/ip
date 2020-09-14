import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        printHello();
        handleCommand();
        printGoodbye();
    }

    private static void handleCommand() {
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            CommandType commandType = CommandType.COMMAND_UNKNOWN;
            try {
                commandType = readCommandType(line);
            } catch (DukeException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            switch(commandType) {
            case COMMAND_BYE:
                break;
            case COMMAND_LIST:
                printTasks();
                break;
            case COMMAND_DONE:
                markAsDone(line);
                break;
            case COMMAND_TODO:
                try {
                    addTodo(line);
                } catch (DukeException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case COMMAND_DEADLINE:
                addDeadline(line);
                break;
            case COMMAND_EVENT:
                addEvent(line);
                break;
            case COMMAND_DELETE:
                deleteEvent(line);
                break;
            case COMMAND_UNKNOWN:
                System.out.println("Unknown command. Type \"help\" for more information");
                break;
            }

            if (commandType == CommandType.COMMAND_BYE) {
                break;
            }
        }
    }

    private static void deleteEvent(String line) {
        int index = Integer.parseInt(line.substring(7, 8));
        System.out.println("Index of delete task" + index);
        Task tempTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Noted! I've removed this task:");
        System.out.println("  " + tempTask);
        String out = (tasks.size() > 1) ? ("Now you have " + tasks.size() + " tasks in the list.") :
                "Now you have 1 task in the list.";
        System.out.println(out);
    }

    private static void addTodo(String line) throws DukeException{

        int i = line.indexOf(' ');
        String taskDescription = (i == -1) ? line : line.substring(i+1);
        if (i == -1) {
            throw new DukeException();
        }
        tasks.add(new Todo(taskDescription));
        printAddNotification();
    }

    private static void addDeadline(String line) {
        int i1 = line.indexOf(' ');
        int i2 = line.indexOf("/by");
        String taskDescription = line.substring(i1+1, i2 - 1);
        String taskDeadline = line.substring(i2+4);
        tasks.add(new Deadline(taskDescription, taskDeadline));
        printAddNotification();
    }

    private static void addEvent(String line) {
        int i1 = line.indexOf(' ');
        int i2 = line.indexOf("/at");
        String taskDescription = line.substring(i1+1, i2 - 1);
        String taskTime = line.substring(i2+4);
        tasks.add(new Event(taskDescription, taskTime));
        printAddNotification();
    }

    private static void printAddNotification() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size()-1));
        String out = (tasks.size() > 1) ? ("Now you have " + tasks.size() + " tasks in the list.") :
                "Now you have 1 task in the list.";
        System.out.println(out);
    }

    private static void markAsDone(String line) {
        int index = Integer.parseInt(line.substring(5, 6));
        tasks.get(index-1).setStatusDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index-1));
    }

    private static void printTasks() {
        int index = 1;
        for (Task task : tasks) {
            System.out.print((index++) + ".");
            System.out.println(task);
        }
    }

    private static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printHello() {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static CommandType readCommandType(String input) throws DukeException{
        int i = input.indexOf(' ');
        String command = (i == -1) ? input : input.substring(0, i);

        switch (command) {
        case ("bye"):
            return CommandType.COMMAND_BYE;
        case ("list"):
            return CommandType.COMMAND_LIST;
        case("todo"):
            return CommandType.COMMAND_TODO;
        case("deadline"):
            return CommandType.COMMAND_DEADLINE;
        case("event"):
            return CommandType.COMMAND_EVENT;
        case("done"):
            return CommandType.COMMAND_DONE;
        case("delete"):
            return CommandType.COMMAND_DELETE;
        default:
            throw new DukeException();
            //return CommandType.COMMAND_UNKNOWN;
        }
    }
}
