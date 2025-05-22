package ntnu.idi.idatt.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import ntnu.idi.idatt.utility.StyleUtil;

/**
 * Component for displaying a money graph.
 * This graph shows the money of each player in TaR.
 */
public class MoneyGraph extends BarChart<String, Number> {

  private final Map<String, XYChart.Data<String, Number>> dataMap = new HashMap<>();

  /**
   * Constructor for the MoneyGraph component.
   *
   * @param player A list of pairs containing player names and their corresponding colors.
   */
  public MoneyGraph(List<Pair<String, Color>> player) {
    super(new CategoryAxis(), new NumberAxis());

    getStyleClass().add("money-graph");

    setCategoryGap(50);

    NumberAxis yAxis = (NumberAxis) getYAxis();
    yAxis.getStyleClass().add("money-graph-yaxis");
    yAxis.setAutoRanging(false);
    yAxis.setLowerBound(0);
    yAxis.setUpperBound(1100);

    CategoryAxis xAxis = (CategoryAxis) getXAxis();
    xAxis.getStyleClass().add("money-graph-xaxis");

    Series<String, Number> series = new Series<>();
    for (Pair<String, Color> pair : player) {
      XYChart.Data<String, Number> data = new XYChart.Data<>(pair.getKey(), 0);
      dataMap.put(pair.getKey(), data);
      series.getData().add(data);

      data.nodeProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
          newValue.setStyle("-fx-bar-fill: " + StyleUtil.toRgbString(pair.getValue()) + ";");
        }
      });
    }

    getData().add(series);

  }

  /**
   * Sets the money value for a specific player.
   *
   * @param name  The name of the player.
   * @param money The amount of money to set.
   */
  public void setMoney(String name, int money) {
    dataMap.get(name).setYValue(money);
  }
}
