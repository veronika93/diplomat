package sk.simonova.veronika.dp.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SettingsController {
    private File mapple;
    private Stage stage;
    @FXML
    private TextField maplePath;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void setMapplePath(MouseEvent event) throws IOException {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        dirChooser.setTitle("Vyberte adresár s programom Mapple");
        mapple = dirChooser.showDialog(stage);
        if(mapple != null){
            maplePath.setText(mapple.getAbsolutePath());
        }

    }



    @FXML
    public void saveSettings(MouseEvent event) throws IOException {
        if(mapple != null){
            SettingsManager mngr = SettingsManager.getInstance();
            mngr.setMaplePath(mapple.getAbsolutePath());
            mngr.save().subscribe();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Pane root = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml")).load();
            stage.setScene(new Scene(root, root.getWidth(), root.getHeight()));
            stage.show();
        } else {
            showFileNotChosen();
        }
    }

    private Alert showFileNotChosen() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba");
        alert.setHeaderText("Domovský priečinom Mapple nebol vybraný.");
        alert.setContentText("Pre pokračovanie vyberte priečinok obsahujúci Mapple.");

        alert.showAndWait();
        return alert;
    }
}
