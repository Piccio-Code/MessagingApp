package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Client extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Design.fxml")));
        Scene scene = new Scene(root, 1200, 720);

        stage.resizableProperty().set(false);
        stage.titleProperty().set("Bro Chat");
        stage.getIcons().add(new Image("GUI/Logo.png"));

        stage.setOnCloseRequest(_ -> System.out.println("Connection Close"));
        stage.setScene(scene);
        stage.show(); // to show the stage
    }
}
