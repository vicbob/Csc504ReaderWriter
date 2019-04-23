package unsynced;

import backend.ReaderWriterProblem;
import homepage.Main;
import javafx.scene.control.Label;

public class Controller {
 public Label lbl;
   public void closeWindow(){
        Main.window.close();
    }

    public void goBack(){
        Main.window.setScene(Main.homepage);
    }

    public void start(){
        int bal = ReaderWriterProblem.start();
        lbl.textProperty().set(""+bal);
    }
}
