package GUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class LoginController {
    @FXML
    TextField username;

    Controller controller;

    Scene scene;

    Stage stage;


    public void initialize() {
        username.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER) && !username.getText().isEmpty()){
                    controller.setName(username.getText());
                    stage.setScene(scene);
                }
            }
        });
    }


}
