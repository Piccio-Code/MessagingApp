package GUI;

import ServerClient.Receiver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class UI extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try (Socket socket = new Socket("localHost", 4444)) {
            FXMLLoader clientLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Design.fxml")));
            FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            String css = Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm();

            Parent loginRoot = loginLoader.load();
            Scene login = new Scene(loginRoot, 1200, 720);


            Parent clientRoot = clientLoader.load();
            Scene client = new Scene(clientRoot, 1200, 720);

            Controller controller = clientLoader.getController();
            LoginController loginController = loginLoader.getController();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            controller.setConnection(socket, out);
            Thread mT = new Thread(new Receiver(in, controller));

            loginController.controller = controller;
            loginController.stage = stage;
            loginController.scene = client;
            loginController.out = out;
            loginController.in = in;
            loginController.thread = mT;

            client.getStylesheets().add(css);
            login.getStylesheets().add(css);

            stage.resizableProperty().set(false);
            stage.titleProperty().set("Bro Chat");
            stage.getIcons().add(new Image("GUI/Logo.jpeg"));
            stage.setOnCloseRequest(_ -> out.println("CLOSE_CONNECTION"));

            stage.setScene(login);
            stage.show();
        } catch (Exception e) {
            System.out.println("ERROR:on connection to the server");
            System.exit(-1);
        }
    }
}
