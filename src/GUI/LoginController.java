package GUI;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginController {
    @FXML
    TextField username;

    Controller controller;

    Scene scene;

    Stage stage;

    PrintWriter out;

    BufferedReader in;

    Thread thread;


    public void initialize() {
        username.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) && !username.getText().isEmpty()){
                String usernameText = username.getText();
                out.println(usernameText);

                try {
                    String response = in.readLine();
                    System.out.println(response);

                    if (response.equals("VALID_USERNAME")){
                        controller.setName(usernameText);
                        stage.setScene(scene);
                        thread.start();
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "ERROR: " + response);
                        username.clear();
                        a.show();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


}
