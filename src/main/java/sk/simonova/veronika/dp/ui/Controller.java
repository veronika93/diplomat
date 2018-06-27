package sk.simonova.veronika.dp.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.exception.NoBracketingException;
import sk.simonova.veronika.dp.plot.Drawer;
import sk.simonova.veronika.dp.plot.FunctionDerivater;
import sk.simonova.veronika.dp.plot.FunctionParser;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


public class Controller {
    @FXML
    TextField alfa;
    @FXML
    TextField delta;
    @FXML
    TextField r;
    @FXML
    TextField kapital;
    @FXML
    TextField cistenie;
    @FXML
    ChoiceBox<String> plusminus;
    @FXML
    TextField vztah;

    @FXML
    public void vypocitaj(MouseEvent event) {

        if(validate()){
            FunctionParser.Result vysledokVztah;
            FunctionParser.Result vysledokKapital;
            FunctionParser.Result vysledokCistenie;
            try {
             vysledokVztah =  FunctionParser.parse(vztah.getText());
             vysledokKapital = FunctionParser.parse(kapital.getText());
             vysledokCistenie = FunctionParser.parse(cistenie.getText());
            }catch (IllegalArgumentException e){
                //todo show error
                return;
            }
            vysledokCistenie = new FunctionParser.Result(vysledokCistenie.getNasobok()*Math.pow(vysledokVztah.getNasobok(),vysledokCistenie.getMocnina()),vysledokCistenie.getMocnina()*vysledokVztah.getMocnina());
            FunctionDerivater.Result pk = FunctionDerivater.derivate(vysledokKapital);
            FunctionDerivater.Result pa = FunctionDerivater.derivate(vysledokCistenie);

            try {
                Scene plot = Drawer.draw(parseTextField(alfa), parseTextField(r), parseTextField(delta), vysledokVztah, pk, pa, getOperation());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(plot);
                stage.show();
            } catch (NoBracketingException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Chyba");
                alert.setHeaderText("Izoklína pre spotrebu nemá reálne riešenie.");
                alert.setContentText("Funkcia "+ r.getText() +"+"+delta.getText()+
                        "-("+ alfa.getText()+"*x^("+alfa.getText()+"-1))-("+
                        plusminus.getValue()+"("+pk.getNasobok()+
                        "*x^"+pk.getMocnina()+")/"+"("+pa.getNasobok()+
                        "x^"+pa.getMocnina()+")) nemá riešenie v obore reálnych čísel. Prosím zadajte inú funkciu znečistenia životného prostredia.");
                alert.showAndWait();
            } catch (ParseException e) {
                //todo neda sa parsovat cislo
                System.out.println("neda sa parsovat cislo");
            }
        } else {
            if(plusminus.getValue() == null){
                plusminus.getStyleClass().add("error");
            }
            //            show error dialog
            showValidationError();

        }
    }

    private Alert showValidationError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba");
        alert.setHeaderText("Niektorý vstupný parameter nebol vyplnený.");
        alert.setContentText("Všetky parametre sú povinné.");

        alert.showAndWait();
        return alert;
    }

    @FXML
    public void initialize(){
        plusminus.setItems(FXCollections.observableArrayList("+", "-"));
    }

    private int getOperation() {
        if (plusminus.getValue() == null) {
            return 0;
        }
        return plusminus.getValue().equals("+") ? 1 : -1;
    }

    private boolean validate(){
        plusminus.getStyleClass().remove("error");
        return alfa.getCharacters().length() > 0 && delta.getCharacters().length() > 0 && r.getCharacters().length() > 0
                && kapital.getCharacters().length() > 0 && cistenie.getCharacters().length() > 0 && vztah.getCharacters().length() > 0 && getOperation() != 0
                ;
    }

    private double parseTextField(TextField textField) throws ParseException {
        String text = textField.getText();
        NumberFormat format;

        if(text.contains(".")){
            format = NumberFormat.getInstance(Locale.US);
        } else {
            format = NumberFormat.getInstance(Locale.GERMAN);
        }
        return format.parse(text).doubleValue();
    }

}
