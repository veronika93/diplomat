package sk.simonova.veronika.dp.plot;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;

/**
 *
 * @author jewelsea
 */
public class PositiveAxes extends Pane {
        private NumberAxis xAxis;
        private NumberAxis yAxis;

        public PositiveAxes(
                int width, int height, double startX, double startY,
                double xLow, double xHi, double xTickUnit,
                double yLow, double yHi, double yTickUnit
        ) {

        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
            setPrefSize(width, height);
            setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

            xAxis = new NumberAxis(xLow, xHi, xTickUnit);
            xAxis.setPadding(new Insets(0));
            xAxis.setSide(Side.BOTTOM);
            xAxis.setMinorTickVisible(false);
            xAxis.setPrefWidth(width);
            xAxis.setPrefHeight(20f);
            xAxis.setLayoutY(startY);
            xAxis.setLayoutX(startX);


            yAxis = new NumberAxis(yLow, yHi, yTickUnit);
            yAxis.setPadding(new Insets(0));
            yAxis.setSide(Side.LEFT);
            yAxis.setMinorTickVisible(false);
            yAxis.setPrefHeight(height);
            yAxis.setPrefWidth(20f);
//            yAxis.layoutXProperty().bind(Bindings.subtract((width / 2) + 1, yAxis.widthProperty()));
            yAxis.setLayoutY(-20f);
            yAxis.setLayoutX(99f);
            getChildren().setAll(xAxis, yAxis);
        }

        public NumberAxis getXAxis() {
            return xAxis;
        }

        public NumberAxis getYAxis() {
            return yAxis;
        }
    }