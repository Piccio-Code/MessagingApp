import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(4444)) {
            HashMap<Integer, PrintWriter> out = new HashMap<>();
            int count = 0;

            while (true) {
                Socket client = ss.accept();

                BufferedReader in= new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.put(count, new PrintWriter(client.getOutputStream(), true));
                Thread clientInit = new Thread(new Broadcast(in, out, client, count));
                count++;
                clientInit.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
