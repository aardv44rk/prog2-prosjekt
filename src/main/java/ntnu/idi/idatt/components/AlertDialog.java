package ntnu.idi.idatt.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import ntnu.idi.idatt.core.AlertDialogType;

public class AlertDialog extends BorderPane {

  TextButton button;

  public AlertDialog(String title, String message, String buttonText, AlertDialogType type) {
    getStyleClass().add("alert-dialog");

    Label titleLabel = new Label(title);
    titleLabel.getStyleClass().add("title");
    if (type == AlertDialogType.ERROR) {
      titleLabel.setText("Error: " + title);
      titleLabel.setStyle("-fx-text-fill: -fx-text-color-error;");
    }
    BorderPane.setAlignment(titleLabel, Pos.CENTER);

    Label messageLabel = new Label(message);
    messageLabel.getStyleClass().add("alert-dialog-message");
    BorderPane.setAlignment(messageLabel, Pos.CENTER);

    button = new TextButton(buttonText);
    BorderPane.setAlignment(button, Pos.CENTER);

    setTop(titleLabel);
    setCenter(messageLabel);
    setBottom(button);

  }

  public void buttonSetOnClick(Runnable runnable) {
    button.setOnAction(e -> runnable.run());
  }
}
