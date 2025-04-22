import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Broadcast implements Runnable {

    private final BufferedReader in;
    private final HashMap<Integer, PrintWriter> out;
    private final Socket connection;
    private final int key;

    public Broadcast(BufferedReader in, HashMap<Integer, PrintWriter> out, Socket connection, int key) {
        this.in = in;
        this.out = out;
        this.key = key;
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            String message = in.readLine();
            boolean end = false;

            while (!end){
                if (message.split(";")[0].equals("END")){
                    message = message.split(";")[1];
                    end = true;
                }

                for (int k : out.keySet()){
                    if (k == key)
                        continue;

                    out.get(k).println(message);
                }

                message = in.readLine();
            }

            out.remove(key);
            connection.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
