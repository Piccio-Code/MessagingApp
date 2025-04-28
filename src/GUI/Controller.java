package GUI;

import ServerClient.Sender;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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
    String name;
    Color color;
    Socket server;
    Sender sender;


    public static final Color[] CONTRASTING_COLORS = {
            Color.RED,          // Rosso acceso
            Color.DARKRED,      // Rosso scuro
            Color.BLUE,         // Blu standard
            Color.DARKBLUE,     // Blu scuro
            Color.GREEN,        // Verde standard
            Color.DARKGREEN,    // Verde scuro
            Color.PURPLE,       // Viola
            Color.DARKMAGENTA,  // Magenta scuro
            Color.ORANGE,       // Arancione
            Color.BLACK,        // Nero (massimo contrasto)
            Color.DARKSLATEGRAY,// Grigio ardesia scuro
            Color.TEAL,         // Verde acqua scuro
            Color.NAVY,         // Blu notte
            Color.MAROON,       // Borgogna
            Color.INDIGO        // Indaco
    };

    public void setConnection(Socket socket, PrintWriter out) {
        server = socket;
        sender = new Sender(out, this);
    }


    @FXML
    public void initialize() throws IOException {
        chatViewerInit();

        sendBtnInit();

        messageFieldInit();

        bottomInit();

        color = CONTRASTING_COLORS[(int) (Math.random() * CONTRASTING_COLORS.length)];
    }

    public void setName(String name) {
        this.name = name.toUpperCase().charAt(0) + name.trim().substring(1).toLowerCase();
    }

    private void send() {
        String message = messageField.getText();

        if (message.isEmpty())
            return;
        HBox log = new HBox();
        String css = getClass().getResource("style.css").toExternalForm();

        Label text = new Label(message);

        text.getStylesheets().add(css);
        text.idProperty().set("Text");

        Label username = new Label(name + ":");

        username.setTextFill(color);
        username.getStylesheets().add(css);
        username.idProperty().set("Username");

        log.getChildren().add(username);
        log.getChildren().add(text);

        chatLog.getChildren().add(log);

        sender.send(message);
        messageField.clear();
    }

    public void display(String message, String name) {
        HBox log = new HBox();
        String css = getClass().getResource("style.css").toExternalForm();

        Label text = new Label(message);

        text.getStylesheets().add(css);
        text.idProperty().set("Text");

        Label username = new Label(name + ":");

        username.setTextFill(color);
        username.getStylesheets().add(css);
        username.idProperty().set("Username");

        log.getChildren().add(username);
        log.getChildren().add(text);

        chatLog.getChildren().add(log);
    }


    private void chatViewerInit() {
        // Set the scroll pane to the right size
        chatViewer.fitToHeightProperty().set(true);
        chatViewer.fitToWidthProperty().set(true);

        // Hide the horizontal and show the vertical one as needed
        chatViewer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chatViewer.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    public String getName() {
        return name;
    }

    private void messageFieldInit() {
        messageField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER))
                send();
        });
    }

    private void sendBtnInit() {
        sendBtn.setOnAction(_ -> send());
    }

    private void bottomInit() {
        bottom.setSpacing(30);
        bottom.setAlignment(Pos.CENTER);
    }
}
