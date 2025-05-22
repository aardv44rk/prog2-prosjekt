package ntnu.idi.idatt.core;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ntnu.idi.idatt.components.PauseMenu;

/**
 * PrimaryScene class represents the main scene of the application.
 * It manages the layout and visibility of the main content and the pause menu.
 */
public class PrimaryScene extends Scene {

  private static final int WIDTH = 1280;
  private static final int HEIGHT = 720;
  private static final StackPane root = new StackPane();
  private final BorderPane borderPane;
  private final PauseMenu pauseMenu;

  /**
   * Constructor for the PrimaryScene class.
   * Initializes the scene with a root layout and sets the dimensions.
   */
  public PrimaryScene() {
    super(root, WIDTH, HEIGHT);

    this.borderPane = new BorderPane();
    this.pauseMenu = new PauseMenu();

    hidePauseMenu();

    root.getChildren().addAll(borderPane, pauseMenu);
  }

  /**
   * Sets the navigation bar at the top of the scene.
   *
   * @param navBar The navigation bar to be set.
   */
  public void setNavBar(Node navBar) {
    borderPane.setTop(navBar);
  }

  /**
   * Sets the content of the scene.
   *
   * @param content The content to be displayed in the center of the scene.
   */
  public void setContent(Parent content) {
    borderPane.setCenter(content);
  }

  /**
   * Adds a node to the scene.
   *
   * @param node The node to be added.
   */
  public void addNode(Node node) {
    root.getChildren().add(node);
    showBlur();
  }

  /**
   * Removes a node from the scene.
   *
   * @param node The node to be removed.
   */
  public void removeNode(Node node) {
    root.getChildren().remove(node);
    hideBlur();
  }

  /**
   * Shows pause menu in the scene.
   */
  public void showPauseMenu() {
    pauseMenu.setVisible(true);
    showBlur();
  }

  /**
   * Hides pause menu in the scene.
   */
  public void hidePauseMenu() {
    pauseMenu.setVisible(false);
    hideBlur();
  }

  /**
   * Blurs the background of the scene.
   */
  private void showBlur() {
    borderPane.setEffect(new GaussianBlur(10));
  }

  /**
   * Removes the blur effect from the background of the scene.
   */
  private void hideBlur() {
    borderPane.setEffect(null);
  }
}