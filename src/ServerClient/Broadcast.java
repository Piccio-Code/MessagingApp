package ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Broadcast implements Runnable {

    private final BufferedReader in;
    private final HashMap<Integer, PrintWriter> out;
    private final int key;
    private final Socket connection;
    private final ArrayList<String> usernames;

    public Broadcast(BufferedReader in, HashMap<Integer, PrintWriter> out, int key, Socket connection, ArrayList<String> usernames) {
        this.in = in;
        this.out = out;
        this.key = key;
        this.usernames = usernames;
        this.connection = connection;
    }

    @Override
    public void run() {


        try {
            while (true) {
                String username = in.readLine().trim().toLowerCase();

                if (username.contains(" "))
                    out.get(key).println("Not space allowed.");

                if (!usernames.contains(username)){
                    out.get(key).println("VALID_USERNAME");
                    usernames.add(username.trim().toLowerCase());
                    break;
                }

                out.get(key).println("Username already taken.");
            }

            System.out.println(usernames);

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
