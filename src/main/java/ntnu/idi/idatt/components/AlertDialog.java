package ntnu.idi.idatt.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import ntnu.idi.idatt.core.AlertDialogType;

/**
 * Component for displaying an alert dialog with a title, message, and button.
 */
public class AlertDialog extends BorderPane {

  TextButton button;

  /**
   * Constructor for the AlertDialog component.
   *
   * @param title       The title of the alert dialog.
   * @param message     The message to be displayed.
   * @param buttonText  The text for the button.
   * @param type        The type of alert dialog (e.g., ERROR, INFO).
   */
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

  /**
   * Sets the action to be performed when the button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void buttonSetOnClick(Runnable runnable) {
    button.setOnAction(e -> runnable.run());
  }
}
