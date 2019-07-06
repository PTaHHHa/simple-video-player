package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("VideoPlayer");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.setFullScreen(true);
//        primaryStage.setFullScreen(KeyCombination.keyCombination("F"));
        //primaryStage.setFullScreenExitHint("Аахахха,еблан");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("ESC"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
