package pl_filippeszke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
        Scene scene = new Scene(parent, 1320, 1000);
        Image icon = new Image(getClass().getResourceAsStream("/img/sun_48px.png"));
        System.setProperty("file.encoding", "ISO-8859-1");
        stage.setScene(scene);
        stage.setTitle("Weather App");
        scene.getStylesheets().add("/css/style.css");
        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.show();
    }
}