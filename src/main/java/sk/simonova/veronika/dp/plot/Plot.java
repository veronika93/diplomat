package sk.simonova.veronika.dp.plot;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jewelsea
 */
public class Plot extends Pane {

    public Plot(double xMin, double xMax, double xInc, double startX, double startY, PositiveAxes axes, List<ColorfulFunction> functions) {

        List<Node> nodes = new ArrayList<>();
        nodes.add(axes);


        for (ColorfulFunction function : functions) {
            Path path = new Path();
            if (function.getPaint() != null) {
                path.setStroke(function.getPaint());
            } else {
                path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 0.6));
            }
            path.setStrokeWidth(2);

            path.setClip(
                    new Rectangle(
                            0, 0,
                            axes.getPrefWidth(),
                            axes.getPrefHeight()
                    )
            );

            double x = xMin;
            boolean firstDotPlotted = false;
            while (x < xMax) {
                try {
                    double result =  function.getFunction().apply(x);
                    if(!Double.isNaN(result) && result >= 0) {
                        double mx = mapX(function.isReverseAxis() ? result : x, axes, startX);
                        double my = mapY(function.isReverseAxis() ? x : result, axes, startY);

                        if (firstDotPlotted) {
                            path.getElements().add(
                                    new LineTo(
                                            mx, my
                                    )
                            );
                        } else {
                            path.getElements().add(
                                    new MoveTo(
                                            mx, my
                                    )
                            );
                            firstDotPlotted = true;
                        }
                    }

                } catch (Exception e) {
                    // not in domain of definition
                } finally {
                    x += xInc;
                }
            }

            nodes.add(path);
        }

        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        getChildren().setAll(nodes);
    }

    private double mapX(double x, PositiveAxes axes, double startX) {
        double sx = axes.getPrefWidth()
                / (axes.getXAxis().getUpperBound()
                - axes.getXAxis().getLowerBound());

        return x * sx + startX;
    }

    private double mapY(double y, PositiveAxes axes, double startY) {
        double sy = axes.getPrefHeight()
                / (axes.getYAxis().getUpperBound()
                - axes.getYAxis().getLowerBound());
        return -y * sy + startY;
    }
}
