import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        try (Socket server = new Socket("localhost", 4444)) {
            Scanner scanner = new Scanner(System.in);
            PrintWriter out = new PrintWriter(server.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));

            Thread rely = new Thread(new RelyMessage(scanner, out, "Client 1"));
            rely.start();

            while (true) {
                String message = in.readLine();

                System.out.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
