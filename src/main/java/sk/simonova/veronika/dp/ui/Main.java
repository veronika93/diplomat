package sk.simonova.veronika.dp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String css = getClass().getClassLoader().getResource("style.css").toExternalForm();

        final Pane root =  FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        primaryStage.setTitle("RAMSEY");
        //testovacie grafy
//        FunctionParser.Result pk = new FunctionParser.Result(12, 3);
//        FunctionParser.Result pa = new FunctionParser.Result(50, -2);
//        FunctionParser.Result vz = new FunctionParser.Result(5, 2);
//        FunctionParser.Result pad = new FunctionParser.Result(pa.getNasobok() * Math.pow(vz.getNasobok(), pa.getMocnina()), pa.getMocnina() * vz.getMocnina());
//
//        Scene scene = Drawer.draw(0.5, 0.1, 0.5, vz, FunctionDerivater.derivate(pk), FunctionDerivater.derivate(pad), 1);
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
