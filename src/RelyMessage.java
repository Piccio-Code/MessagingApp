import java.io.PrintWriter;
import java.util.Scanner;

public class RelyMessage implements Runnable{
    private final Scanner scanner;
    private final PrintWriter out;
    private final String name;

    public RelyMessage(Scanner scanner, PrintWriter out, String name) {
        this.scanner = scanner;
        this.out = out;
        this.name = name;
    }

    @Override
    public void run() {
        String message = name + ": " + scanner.nextLine();

        while (true){
            out.println(message);
            message = name + ": " + scanner.nextLine();
        }
    }
}
