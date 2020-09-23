import java.util.Scanner;

public class Parser {

    /**
     * Read in commands from users and process them until the "bye" command is input
     */
    protected static void handleCommand() {
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
                TaskList.markAsDone(line);
                break;
            case COMMAND_TODO:
                try {
                    TaskList.addTodo(line);
                } catch (DukeException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case COMMAND_DEADLINE:
                TaskList.addDeadline(line);
                break;
            case COMMAND_EVENT:
                TaskList.addEvent(line);
                break;
            case COMMAND_DELETE:
                TaskList.deleteEvent(line);
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

    /**
     * Returns the of type of the input command.
     *
     * @param input  X coordinate of position.
     * @return one type of the command.
     * @throws DukeException If the input command is not in the list of known commands.
     */
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
