package homepage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class Controller {
    Button sync;

    public void goToUnsynchronized(){
        Main.window.setScene(Main.unsyncedpage);
    }

    public void goToSynchronized(){
            Main.window.setScene(Main.syncedpage);
    }

   public void closeWindow(){
        Main.window.close();
    }
}
