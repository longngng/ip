package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the existing list of tasks from file.
     * If the folder or file do not exist, create it
     */
    public void loadExistingList() {
        //If the folder doesn't exists, create it
        File folder = new File("data");
        boolean bool = folder.mkdirs();

        //If the file doesn't exist, create it
        File f = new File(filePath);
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

    /**
     * Reads the existing tasks listed in the file into the ArrayList
     */
    private static void parseTasks(Scanner s) {
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String[] data = currentLine.split("\\|");

            for (int i = 0; i < data.length; i++) {
                data[i] = data[i].trim();
            }

            if (data[0].equals("T")) {
                tasks.add(new Todo(data[2]));
                if (data[1].equals("1")) {
                    tasks.get(tasks.size() - 1).setStatusDone();
                }
            } else if (data[0].equals("D")) {
                tasks.add(new Deadline(data[2], data[3]));
                if (data[1].equals("1")) {
                    tasks.get(tasks.size() - 1).setStatusDone();
                }
            } else if (data[0].equals("E")) {
                tasks.add(new Event(data[2], data[3]));
                if (data[1].equals("1")) {
                    tasks.get(tasks.size() - 1).setStatusDone();
                }
            } else {
                System.out.println("Error");
            }
        }
    }

    /**
     * Writes text into output file
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Update the output file when a new task is added, deleted or marked as done
     */
    public static void updateDataFile() {
        String file2 = filePath;
        String stringOfTasks = "";
        for (Task task : tasks) {

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
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Return the the list of the task in the file and call the loadExistingList() function.
     *
     * @return the list of the task at the beginning when the program is started.
     */
    public ArrayList<Task> load() {
        loadExistingList();
        return tasks;
    }
}
