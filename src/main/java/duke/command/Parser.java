package duke.command;

import duke.DukeException;

import java.util.Scanner;

public class Parser {

    /**
     * Read in commands from users and process them until the "bye" command is input
     */
    public static void handleCommand() {
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
                TaskList.printTasks();
                break;
            case COMMAND_DONE:
                try {
                    TaskList.markAsDone(line);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Cannot find the index of the task to be deleted");
                } catch (NumberFormatException e) {
                    System.out.println("Please key in a positive number for the index");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The index is out of bound. Use `list` to check the index of the tasks");
                }
                break;
            case COMMAND_TODO:
                try {
                    TaskList.addTodo(line);
                } catch (DukeException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case COMMAND_DEADLINE:
                try {
                    TaskList.addDeadline(line);
                } catch (DukeException e) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Cannot find the start of input time. Type help for more info.");
                }

                break;
            case COMMAND_EVENT:
                try {
                    TaskList.addEvent(line);
                } catch (DukeException e) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Cannot find the start of input time. Type help for more info.");
                }
                break;
            case COMMAND_DELETE:
                try {
                    TaskList.deleteEvent(line);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Cannot find the index of the task to be deleted");
                } catch (NumberFormatException e) {
                    System.out.println("Please key in a positive number for the index");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The index is out of bound. Use `list` to check the index of the tasks");
                }
                break;
            case COMMAND_FIND:
                TaskList.findTasks(line);
                break;
            case COMMAND_HELP:
                printCommandList();
                break;
            case COMMAND_EMPTY:
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

    private static void printCommandList() {
        System.out.println(
                "Below is the list of available commands:\n" +
                "todo\n" + "   `todo <DESCRIPTION>` adds a new todo task\n" +
                "event\n" + "   `event <DESCRIPTION> /at <DATE>` adds a new event task\n" +
                        "   <DATE> must be in format yyyy-mm-dd\n" +
                "deadline\n" + "    `deadline <DESCRIPTION> /by <DATE>` adds a new deadline task\n" +
                        "   <DATE> must be in format yyyy-mm-dd\n" +
                "list\n" + "    `list` lists out the existing tasks\n" +
                "done\n" + "    `done <INDEX>` marks a task as done\n" +
                "find\n" + "    `find <KEYWORD>` lists out the tasks that contain a keyword\n" +
                "delete\n" + "    `delete <INDEX>` deletes a task in the list\n" +
                "bye\n" + "    `bye` terminates the program\n" +
                "help\n" + "    `help` prints out the list of available commands and their descriptions");
    }

    /**
     * Returns the of type of the input command.
     *
     * @param input  X coordinate of position.
     * @return one type of the command.
     * @throws DukeException If the input command is not in the list of known commands.
     */
    private static CommandType readCommandType(String input) throws DukeException {
        int i = input.indexOf(' ');
        String command = (i == -1) ? input : input.substring(0, i);

        switch (command) {
        case(""):
            return CommandType.COMMAND_EMPTY;
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
        case("find"):
            return CommandType.COMMAND_FIND;
        case("help"):
            return CommandType.COMMAND_HELP;
        default:
            throw new DukeException();
        }
    }
}
