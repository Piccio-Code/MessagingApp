import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        try (Socket server = new Socket("localhost", 4444)) {

            Scanner scanner = new Scanner(System.in);

            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter out = new PrintWriter(server.getOutputStream(), true);

            Thread myTh = new Thread(new SendMessage(scanner, out));

            myTh.start();
            String message = in.readLine();

            while (!message.equals("END")) {
                System.out.println(message);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
