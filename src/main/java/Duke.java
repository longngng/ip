import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static int MAX_TASK = 100;

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_TASK];
        int taskCount = 0;

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");

        while (true) {
            line = in.nextLine();
            if(line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                int i = 0;
                while (i < tasks.length) {
                    if(tasks[i] == null) {
                        break;
                    }
                    System.out.println((i+1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                    i++;
                }
            } else {
                if (line.length() >= 4 && line.substring(0,4).equals("done")) {
                    int index = Integer.parseInt(line.substring(5,6));
                    tasks[index-1].setStatusDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(("  [" + tasks[index-1].getStatusIcon() + "] " + tasks[index-1].getDescription()));
                } else {
                    tasks[taskCount] = new Task(line);
                    System.out.println("added: " + line);
                    taskCount++;
                }

            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
