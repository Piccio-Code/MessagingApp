import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        System.out.println("Waiting .... ");
        try (ServerSocket ss = new ServerSocket(4444)) {

            Socket client = ss.accept();
            Scanner scanner = new Scanner(System.in);

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            Thread myTh = new Thread(new SendMessage(scanner, out));

            myTh.start();
            String message = in.readLine();

            while (!message.equals("END")) {
                System.out.println(message);
            }

            client.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
