package sk.simonova.veronika.dp.ui;

import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String css = getClass().getClassLoader().getResource("style.css").toExternalForm();

        final Pane root =  FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        primaryStage.setTitle("RAMSEY");
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
