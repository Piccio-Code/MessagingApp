import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Client2 {
    public static void main(String[] args) {
        try (Socket server = new Socket("localhost", 4444)) {
            Scanner scanner = new Scanner(System.in);
            PrintWriter out = new PrintWriter(server.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));

            Thread rely = new Thread(new RelyMessage(scanner, out, "Client2", server));
            rely.start();

            while (server.isConnected()) {
                String message = in.readLine();

                System.out.println(message);
            }
        } catch (Exception e) {
            System.out.println("The client is disconnected");
        }


    }
}
