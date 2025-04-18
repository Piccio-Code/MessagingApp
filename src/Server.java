import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(4444)) {
            ArrayList<PrintWriter> out = new ArrayList<>();
            while (true) {
                Socket client = ss.accept();

                BufferedReader in= new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.add(new PrintWriter(client.getOutputStream(), true));

                Thread clientInit = new Thread(new Broadcast(in, out));

                clientInit.start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
