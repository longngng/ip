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
            case COMMAND_ADD:
                addTask(line);
                break;
            }

            if (commandType == CommandType.COMMAND_BYE) {
                break;
            }
        }
    }

    private static void addTask(String line) {
        tasks[taskCount] = new Task(line);
        System.out.println("added: " + line);
        taskCount++;
    }

    private static void markAsDone(String line) {
        int index = Integer.parseInt(line.substring(5, 6));
        tasks[index - 1].setStatusDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(("  [" + tasks[index - 1].getStatusIcon() + "] " + tasks[index - 1].getDescription()));
    }

    private static void printTasks() {
        int i = 0;
        while (i < tasks.length) {
            if (tasks[i] == null) {
                break;
            }
            System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
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

    private static CommandType readCommandType(String command) {
        switch (command) {
        case ("bye"):
            return CommandType.COMMAND_BYE;
        case ("list"):
            return CommandType.COMMAND_LIST;
        default:
            if (command.length() >= 4 && command.substring(0, 4).equals("done"))
                return CommandType.COMMAND_DONE;
            return CommandType.COMMAND_ADD;
        }
    }
}
