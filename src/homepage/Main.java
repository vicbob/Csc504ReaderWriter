package homepage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage window;
    public static Scene homepage;
    public static Scene syncedpage;
    public static Scene unsyncedpage;


    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Parent sync = FXMLLoader.load(getClass().getResource("../synced/synced.fxml"));
        Parent unsync = FXMLLoader.load(getClass().getResource("../unsynced/unsynced.fxml"));

        homepage = new Scene(root);
        syncedpage = new Scene(sync);
        unsyncedpage = new Scene(unsync);

        window.setTitle("Reader Writer Problem");
        window.setScene(homepage);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


