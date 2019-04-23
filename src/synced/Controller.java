package synced;

import backend.ReaderWriter;
import homepage.Main;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Label lbl;
    static IntegerProperty integerProperty;


    public void closeWindow() {
        Main.window.close();
    }

    public void goBack() {
        Main.window.setScene(Main.homepage);
    }
    public void start(){
        int bal = ReaderWriter.start();
        lbl.textProperty().set(""+bal);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        integerProperty = new SimpleIntegerProperty(0);
    }
}


