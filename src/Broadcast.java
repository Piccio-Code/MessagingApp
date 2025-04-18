import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Broadcast implements Runnable {

    private final BufferedReader in;
    private final ArrayList<PrintWriter> out;
    private final int index;

    public Broadcast(BufferedReader in, ArrayList<PrintWriter> out) {
        this.in = in;
        this.out = out;
        index = out.size() - 1;
    }

    @Override
    public void run() {
        try {
            String message = in.readLine();

            while (true){
                for (int i = 0; i < out.size(); i++){
                    if (i == index)
                        continue;

                    out.get(i).println(message);
                }

                message = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
