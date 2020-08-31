import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static int MAX_TASK = 100;
    static Task[] tasks = new Task[MAX_TASK];
    public static int taskCount = 0;

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
            CommandType commandType = readCommandType(line);

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
                addTodo(line);
                break;
            case COMMAND_DEADLINE:
                addDeadline(line);
                break;
            case COMMAND_EVENT:
                addEvent(line);
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

    private static void addTodo(String line) {
        int i = line.indexOf(' ');
        String taskDescription = (i == -1) ? line : line.substring(i+1);
        tasks[taskCount] = new Todo(taskDescription);
        printAddNotification();
    }

    private static void addDeadline(String line) {
        int i1 = line.indexOf(' ');
        int i2 = line.indexOf("/by");
        String taskDescription = line.substring(i1+1, i2 - 1);
        String taskDeadline = line.substring(i2+4);
        tasks[taskCount] = new Deadline(taskDescription, taskDeadline);
        printAddNotification();
    }

    private static void addEvent(String line) {
        int i1 = line.indexOf(' ');
        int i2 = line.indexOf("/at");
        String taskDescription = line.substring(i1+1, i2 - 1);
        String taskTime = line.substring(i2+4);
        tasks[taskCount] = new Event(taskDescription, taskTime);
        printAddNotification();
    }

    private static void printAddNotification() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount]);
        taskCount++;
        String out = (taskCount > 1) ? ("Now you have " + taskCount + " tasks in the list.") :
                "Now you have 1 task in the list.";
        System.out.println(out);
    }

    private static void markAsDone(String line) {
        int index = Integer.parseInt(line.substring(5, 6));
        tasks[index - 1].setStatusDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[index - 1]);
    }

    private static void printTasks() {
        int i = 0;
        while (i < tasks.length) {
            if (tasks[i] == null) {
                break;
            }
            System.out.print((i + 1) + ".");
            System.out.println(tasks[i]);
            i++;
        }
    }

    private static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printHello() {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static CommandType readCommandType(String input) {
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
        default:
            return CommandType.COMMAND_UNKNOWN;
        }
    }
}
