package GUI;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    ScrollPane chatViewer;
    @FXML
    VBox chatLog;
    @FXML
    TextField messageField;
    @FXML
    Button sendBtn;
    @FXML
    HBox bottom;

    @FXML
    public void initialize() {
        chatViewerInit();

        sendBtnInit();

        messageFieldInit();

        bottomInit();
    }

    private void send() {
        String message = messageField.getText();

        chatLog.getChildren().add(new Label(message));

        messageField.clear();
    }

    private void chatViewerInit() {
        // Set the scroll pane to the right size
        chatViewer.fitToHeightProperty().set(true);
        chatViewer.fitToWidthProperty().set(true);

        // Hide the horizontal and show the vertical one as needed
        chatViewer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chatViewer.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    private void messageFieldInit() {
        messageField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER))
                send();
        });
    }

    private void sendBtnInit() {
        sendBtn.setOnAction(_ -> send());
        sendBtn.setText("Send");
    }

    private void bottomInit() {
        bottom.setSpacing(30);
        bottom.setAlignment(Pos.CENTER);
    }
}
