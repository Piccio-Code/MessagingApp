package ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Broadcast implements Runnable {

    private final BufferedReader in;
    private final HashMap<Integer, PrintWriter> out;
    private final int key;
    private final Socket connection;
    private final  HashMap<Integer, String> usernames;

    public Broadcast(BufferedReader in, HashMap<Integer, PrintWriter> out, int key, Socket connection,  HashMap<Integer, String> usernames) {
        this.in = in;
        this.out = out;
        this.key = key;
        this.usernames = usernames;
        this.connection = connection;
    }

    @Override
    public void run() {


        try {
            setUsername();

            String message = in.readLine();

            while (!message.equals("CLOSE_CONNECTION")){
                for (int k : out.keySet()){
                    if (k == key)
                        continue;

                    out.get(k).println(message);
                }

                message = in.readLine();
            }

            usernames.remove(key);
            out.remove(key);
            connection.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUsername() throws IOException {
        while (true) {
            String username = in.readLine().trim().toLowerCase();

            if (username.contains(" "))
                out.get(key).println("Not space allowed.");

            synchronized (usernames) {
                if (!usernames.containsValue(username)){
                    out.get(key).println("VALID_USERNAME");
                    usernames.put(key, username.trim().toLowerCase());
                    break;
                }
            }
            out.get(key).println("Username already taken.");
        }

        System.out.println(usernames);
    }
}
