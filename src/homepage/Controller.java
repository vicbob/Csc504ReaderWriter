package homepage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class Controller {
    Button sync;

    public void goToUnsynchronized(){
        Main.window.setScene(Main.unsyncedpage);
//        Main.window.setMaximized(false);
        Main.window.setMaximized(true);
    }

    public void goToSynchronized(){
        Main.window.setScene(Main.syncedpage);
//        Main.window.setMaximized(false);
        Main.window.setMaximized(true);
    }

   public void closeWindow(){
        Main.window.close();
    }
}
