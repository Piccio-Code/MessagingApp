package ServerClient;

import GUI.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Sender{
    PrintWriter out;
    Controller controller;
    Socket connection;

    public Sender(PrintWriter out, Controller controller, Socket connection) {
        this.out = out;
        this.controller = controller;
        this.connection = connection;
    }


    public void send(String message) {
        if (message.equals("CLOSE_CONNECTION")){
            out.println("CLOSE_CONNECTION");
            try {
                connection.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        out.println(controller.getName() + ";" + message + ";" + controller.getColor().toString());
    }
}
