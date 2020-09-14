import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    public static int MAX_TASK = 100;
    static Task[] tasks = new Task[MAX_TASK];
    public static int taskCount = 0;

    public static void main(String[] args) {
        loadExistingList();
        printHello();
        handleCommand();
        printGoodbye();
        updateDataFile();
    }

    private static void loadExistingList() {
        //If the folder doesn't exists, create it
        File folder = new File("data");
        boolean bool = folder.mkdirs();
        if(bool){
            System.out.println("Directory created successfully");
        }else{
            System.out.println("Sorry couldn't create specified directory");
        }

        //If the file doesn't exist, create it
        File f = new File("data/duke.txt");
        Scanner s = null; // create a Scanner using the File as the source
        try {
            s = new Scanner(f);
            parseTasks(s);
        } catch (FileNotFoundException e) {
            //System.out.println("File Not Found");
            //e.printStackTrace();
            try {
                f.createNewFile();
                //System.out.println("New File");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private static void parseTasks(Scanner s) {
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String[] data = currentLine.split("\\|");

            for (int i = 0; i < data.length; i++) {
                data[i] = data[i].trim();
            }

            if (data[0].equals("T")) {
                tasks[taskCount] = new Todo(data[2]);
                if (data[1].equals("1")) {
                    tasks[taskCount].setStatusDone();
                }
            } else if (data[0].equals("D")) {
                tasks[taskCount] = new Deadline(data[2], data[3]);
                if (data[1].equals("1")) {
                    tasks[taskCount].setStatusDone();
                }
            } else if (data[0].equals("E")) {
                tasks[taskCount] = new Event(data[2],data[3]);
                if (data[1].equals("1")) {
                    tasks[taskCount].setStatusDone();
                }
            } else {
                System.out.println("Error");
            }
            taskCount++;
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    private static void updateDataFile() {
        String file2 = "data/duke.txt";
        String stringOfTasks = "";
        for (int i = 0; i < taskCount; i++) {
            Task task = tasks[i];
            if (task instanceof Event) {
                Event temp = (Event) task;
                int done = temp.getDoneStatus();
                stringOfTasks += "E" + " | " + done + " | " + temp.getDescription() + " | " + temp.getTime() + System.lineSeparator();
            } else if (task instanceof Deadline) {
                Deadline temp = (Deadline) task;
                int done = temp.getDoneStatus();
                stringOfTasks += "D" + " | " + done + " | " + temp.getDescription() + " | " + temp.getBy() + System.lineSeparator();
            } else if (task instanceof Todo) {
                Todo temp = (Todo) task;
                int done = temp.getDoneStatus();
                stringOfTasks += "T" + " | " + done + " | " + temp.getDescription() + System.lineSeparator();
            } else {
                System.out.println("Error");
            }
        }
        try {
            writeToFile(file2, stringOfTasks);
            //writeToFile(file2, "first line" + System.lineSeparator() + "second line");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
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
            case COMMAND_UNKNOWN:
                System.out.println("Unknown command. Type \"help\" for more information");
                break;
            }

            if (commandType == CommandType.COMMAND_BYE) {
                break;
            }
        }
    }

    private static void addTodo(String line) throws DukeException{

        int i = line.indexOf(' ');
        String taskDescription = (i == -1) ? line : line.substring(i+1);
        if (i == -1) {
            throw new DukeException();
        }
        tasks[taskCount] = new Todo(taskDescription);
        printAddNotification();
        updateDataFile();
    }



    private static void addDeadline(String line) {
        int i1 = line.indexOf(' ');
        int i2 = line.indexOf("/by");
        String taskDescription = line.substring(i1+1, i2 - 1);
        String taskDeadline = line.substring(i2+4);
        tasks[taskCount] = new Deadline(taskDescription, taskDeadline);
        printAddNotification();
        updateDataFile();
    }

    private static void addEvent(String line) {
        int i1 = line.indexOf(' ');
        int i2 = line.indexOf("/at");
        String taskDescription = line.substring(i1+1, i2 - 1);
        String taskTime = line.substring(i2+4);
        tasks[taskCount] = new Event(taskDescription, taskTime);
        printAddNotification();
        updateDataFile();
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
        updateDataFile();
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
        default:
            throw new DukeException();
            //return CommandType.COMMAND_UNKNOWN;
        }
    }
}
