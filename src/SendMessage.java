import java.io.PrintWriter;
import java.util.Scanner;

public class SendMessage implements Runnable{

    Scanner scanner;
    PrintWriter out;

    public SendMessage(Scanner scanner, PrintWriter out) {
        this.scanner = scanner;
        this.out = out;
    }

    @Override
    public void run() {
        String message = scanner.nextLine();

        while (!message.isEmpty()) {
            out.println(message);
            message = scanner.nextLine();
        }

        out.println("END");
    }
}
