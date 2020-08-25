import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            if(line.equals("bye")) {
                break;
            } else {
                System.out.println(line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
