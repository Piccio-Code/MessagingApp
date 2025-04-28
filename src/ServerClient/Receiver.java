package ServerClient;

import GUI.Controller;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;

public class Receiver implements Runnable{
    BufferedReader in;
    Controller controller;
    String message;

    public Receiver(BufferedReader in, Controller controller) {
        this.in = in;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            message = in.readLine();

            while (true) {
                Platform.runLater(() -> {
                    controller.display(message.trim().split(";")[1], message.trim().split(";")[0], message.trim().split(";")[2]);
                });

                message = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
