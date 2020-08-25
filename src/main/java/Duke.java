import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        
        String[] tasks = new String[100];
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
                    System.out.println((i+1) + ". " + tasks[i]);
                    i++;
                }
            } else {
                tasks[taskCount] = line;
                System.out.println("added: " + line);
                taskCount++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
