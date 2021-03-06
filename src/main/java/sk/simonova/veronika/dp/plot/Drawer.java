package sk.simonova.veronika.dp.plot;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Drawer {

    public static final String PRODUKCNA = "Produkcna";
    public static final String IZOKLINA_C = "Izoklina C";
    public static final String IZOKLINA_K = "Izoklina K";
    public static final String ROZSIRENA_IZOKLINA_C = "Rozsirena izoklina C";
    public static final String ROZSIRENA_IZOKLINA_K = "Rozsirena izoklina K";

    public static Scene draw(double alfa, double r, double delta, FunctionParser.Result vztah, FunctionDerivater.Result pk, FunctionDerivater.Result pa, int znamienko) {
        ImageView backView = buildBackArrow();
        Function<Double, Double> produkcna = x -> Math.pow(x, alfa);
        Function<Double, Double> izoklinaC = x -> Math.pow((r + delta) / alfa, 1 / (alfa - 1));
        Function<Double, Double> izoklikaK = x -> produkcna.apply(x) - (delta * x);

        UnivariateFunction function = x -> r + delta - (alfa * Math.pow(x, (alfa - 1))) - (znamienko * ((pk.getNasobok() * (Math.pow(x, pk.getMocnina()))) / (pa.getNasobok() * (Math.pow(x, pa.getMocnina())))));

        UnivariateSolver solver = new BisectionSolver();
//                new BracketingNthOrderBrentSolver(1.0e-12, 1.0e-8, 5);
        double c = solver.solve(100, function, 0.00001, 10.0, 0);
        Function<Double, Double> rozsirIzoklikaC = x -> c;
        Function<Double, Double> rozsirIzoklikaK = x -> produkcna.apply(x) - (delta * x) - (vztah.getNasobok() * Math.pow(x, vztah.getMocnina()));
        BorderPane bp = new BorderPane();

        Plot plot;

        double startX = 120f;
        double startY = 580;

        PositiveAxes axes = new PositiveAxes(
                1000, 600, startX, startY,
                0, 5, 0.1,
                0, 1, 0.01
        );

        List<ColorfulFunction> functions = new ArrayList<>();
        functions.add(new ColorfulFunction(produkcna, Color.GREEN, PRODUKCNA));
        functions.add(new ColorfulFunction(izoklinaC, Color.RED, true, IZOKLINA_C));
        functions.add(new ColorfulFunction(izoklikaK, Color.BLUE, IZOKLINA_K));
        functions.add(new ColorfulFunction(rozsirIzoklikaK, Color.PURPLE, ROZSIRENA_IZOKLINA_K));
        functions.add(new ColorfulFunction(rozsirIzoklikaC, Color.ORANGE, true, ROZSIRENA_IZOKLINA_C));


        plot = new Plot(0, 5, 0.01, startX, startY, axes, functions);

        List<PlotLegend.Item> items = new ArrayList<>();
        items.add(new PlotLegend.Item("produkcna", PRODUKCNA, () -> plot.toggleNode(PRODUKCNA)));
        items.add(new PlotLegend.Item("izoklinaC", IZOKLINA_C, () -> plot.toggleNode(IZOKLINA_C)));
        items.add(new PlotLegend.Item("izoklinaK", IZOKLINA_K, () -> plot.toggleNode(IZOKLINA_K)));
        items.add(new PlotLegend.Item("rozsirIzoklinaC", ROZSIRENA_IZOKLINA_C, () -> plot.toggleNode(ROZSIRENA_IZOKLINA_C)));
        items.add(new PlotLegend.Item("rozsirIzoklinaK", ROZSIRENA_IZOKLINA_K, () -> plot.toggleNode(ROZSIRENA_IZOKLINA_K)));

        VBox legend = PlotLegend.buildLegend(items);
        legend.setPrefWidth(100);
        legend.setPrefHeight(300);
        legend.setTranslateY(20f);
        legend.setTranslateX(800f);
        StackPane layout = new StackPane(plot, backView, legend);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: white;");


        bp.setCenter(layout);


        return new Scene(bp, Color.rgb(35, 39, 50));
    }

    private static ImageView buildBackArrow() {
        Image back = new Image(Drawer.class.getClassLoader().getResourceAsStream("back.png"));
        ImageView backView = new ImageView(back);
        backView.setX(10f);
        backView.setY(10f);
        backView.setFitHeight(50f);
        backView.setFitWidth(50f);
        backView.setOnMouseClicked(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Pane root = null;
            try {
                root = FXMLLoader.load(Drawer.class.getClassLoader().getResource("main.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("RAMSEY");
            Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
            stage.setScene(scene);
            stage.show();
        });
        return backView;
    }
}
