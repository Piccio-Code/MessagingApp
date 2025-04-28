package ServerClient;

import GUI.Controller;

import java.io.PrintWriter;

public class Sender{
    PrintWriter out;
    Controller controller;

    public Sender(PrintWriter out, Controller controller) {
        this.out = out;
        this.controller = controller;
    }


    public void send(String message) {
        out.println(controller.getName() + ";" + message);
    }
}
