package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class UI extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader clientLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Design.fxml")));
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        String css = getClass().getResource("style.css").toExternalForm();

        Parent loginRoot = loginLoader.load();
        Scene login = new Scene(loginRoot, 1200, 720);


        Parent clientRoot = clientLoader.load();
        Scene client = new Scene(clientRoot, 1200, 720);

        Controller controller = clientLoader.getController();
        LoginController loginController = loginLoader.getController();

        client.getStylesheets().add(css);
        login.getStylesheets().add(css);

        loginController.controller = controller;
        loginController.stage = stage;
        loginController.scene = client;

        stage.resizableProperty().set(false);
        stage.titleProperty().set("Bro Chat");
        stage.getIcons().add(new Image("GUI/Logo.jpeg"));
        stage.setOnCloseRequest(_ -> System.out.println("Connection Close"));

        stage.setScene(login);
        stage.show();
    }
}
