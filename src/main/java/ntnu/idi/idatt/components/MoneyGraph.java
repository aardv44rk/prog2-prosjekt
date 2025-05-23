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

  private static final double TARGET_BAR_WIDTH = 30.0;
  private static final double COMPONENT_TOTAL_WIDTH = 300.0;
  private static final double MAGIC_NUMBER = 140.0;

  private final Map<String, XYChart.Data<String, Number>> dataMap = new HashMap<>();

  /**
   * Constructor for the MoneyGraph component.
   *
   * @param players A list of pairs containing player names and their corresponding colors.
   */
  public MoneyGraph(List<Pair<String, Color>> players) {
    super(new CategoryAxis(), new NumberAxis());

    getStyleClass().add("money-graph");

  
    NumberAxis yAxis = (NumberAxis) getYAxis();
    yAxis.getStyleClass().add("money-graph-yaxis");
    yAxis.setAutoRanging(false);
    yAxis.setLowerBound(0);
    yAxis.setUpperBound(1150);

    CategoryAxis xAxis = (CategoryAxis) getXAxis();
    xAxis.getStyleClass().add("money-graph-xaxis");
    xAxis.setStartMargin(0.0);
    xAxis.setEndMargin(0.0);

    Series<String, Number> series = new Series<>();
    for (Pair<String, Color> pair : players) {
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

    setBarGap(0.0);

    int playerCount = players.size();

    if (playerCount <= 0) {
      setCategoryGap(0);
    } else if (playerCount == 1) {
      setCategoryGap(0);
    } else {
      double effectivePlotAreaWidth = COMPONENT_TOTAL_WIDTH - MAGIC_NUMBER;
      double totalDesiredBarWidths = TARGET_BAR_WIDTH * playerCount;

      double calculatedCategoryGap = (effectivePlotAreaWidth - totalDesiredBarWidths) / (playerCount - 1);

      setCategoryGap(Math.max(0.0, calculatedCategoryGap));
    }
  
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
