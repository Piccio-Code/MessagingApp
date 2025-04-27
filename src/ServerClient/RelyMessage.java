package ServerClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class RelyMessage implements Runnable{
    private final Scanner scanner;
    private final PrintWriter out;
    private final String name;
    private final Socket server;

    public RelyMessage(Scanner scanner, PrintWriter out, String name, Socket server) {
        this.scanner = scanner;
        this.out = out;
        this.name = name;
        this.server = server;
    }

    @Override
    public void run() {
        String message = name + ": " + scanner.nextLine();

        while (!message.equals(name + ": " + "END")){
            out.println(message);
            message = name + ": " + scanner.nextLine();
        }

        out.println("END" + ";" + "The " + name + " has disconnected.");
        try {
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
