package homepage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import synced.Controller;

import java.awt.*;

public class Main extends Application {
    public static Stage window;
    public static Scene homepage;
    public static Scene syncedpage;
    public static Scene unsyncedpage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        Parent root = FXMLLoader.load(Main.class.getResource("homepage.fxml"));
        Parent sync = FXMLLoader.load(Controller.class.getResource("synced.fxml"));
        Parent unsync = FXMLLoader.load(unsynced.Controller.class.getResource("unsynced.fxml"));

        homepage = new Scene(root);
        syncedpage = new Scene(sync);
        unsyncedpage = new Scene(unsync);

//        styles
        homepage.getStylesheets().addAll(Main.class.getResource("homepage.css").toExternalForm());
        syncedpage.getStylesheets().addAll(synced.Controller.class.getResource("sync.css").toExternalForm());
        unsyncedpage.getStylesheets().addAll(unsynced.Controller.class.getResource("unsync.css").toExternalForm());

        window.setTitle("Reader Writer Problem");
        window.setMinWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        window.setMinHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        window.setScene(homepage);
//        window.setMaximized(false);
        window.setMaximized(true);
//        window.setMinHeight(768);
//        window.setMinWidth(1366);
        window.setResizable(false);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


