package ntnu.idi.idatt.components;

import javafx.scene.control.Label;

public class UIDie extends Label {

  public UIDie(int eyes) {
    getStyleClass().add("die");
    this.setText(Integer.toString(eyes));
  }

  public void setEyes(int eyes) {
    setText(Integer.toString(eyes));
  }
}
