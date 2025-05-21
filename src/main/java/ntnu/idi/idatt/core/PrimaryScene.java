package ntnu.idi.idatt.core;

import java.util.function.Consumer;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
// import ntnu.idi.idatt.AppState;
// import ntnu.idi.idatt.components.PauseMenu;

public class PrimaryScene extends Scene {

  private static final int WIDTH = 1280;
  private static final int HEIGHT = 720;
  private static final StackPane root = new StackPane();
  private final BorderPane borderPane;
  // private final PauseMenu pauseMenu;
  private Consumer<String> saveGameAction;

  public PrimaryScene() {
    super(root, WIDTH, HEIGHT);
    this.borderPane = new BorderPane();
    // this.pauseMenu = new PauseMenu();

    // pauseMenu.saveGameButtonSetOnClick(() -> {
    //   if (saveGameAction != null) {
    //     String gameName = AppState.getSelectedGame() != null 
    //         ? AppState.getSelectedGame().getName().replaceAll("\\s+","_") : "default";
    //     String fileName = gameName + "_save_" + System.currentTimeMillis() + ".json";
    //     saveGameAction.accept(fileName);
    //     Router.showAlert("Game saved", "Game saved to " + fileName, "OK", null);
    //   } else {
    //     Router.showAlert("Error", "Cannot save game right now.", "OK", null);  
    //   }
    // });
    // hidePauseMenu();

    root.getChildren().addAll(borderPane);
  }

  public void setNavBar(Node navBar) {
    borderPane.setTop(navBar);
  }

  public void setContent(Parent content) {
    borderPane.setCenter(content);
  }

  public void addNode(Node node) {
    root.getChildren().add(node);
    showBlur();
  }

  public void removeNode(Node node) {
    root.getChildren().remove(node);
    hideBlur();
  }

  // public void showPauseMenu() {
  //   pauseMenu.setVisible(true);
  //   showBlur();
  // }

  // public void hidePauseMenu() {
  //   pauseMenu.setVisible(false);
  //   hideBlur();
  // }

  private void showBlur() {
    borderPane.setEffect(new GaussianBlur(10));
  }

  private void hideBlur() {
    borderPane.setEffect(null);
  }

  public Consumer<String> getSaveGameAction() {
    return saveGameAction;
  }

  public void setSaveGameAction(Consumer<String> saveGameAction) {
    this.saveGameAction = saveGameAction;
  }
}