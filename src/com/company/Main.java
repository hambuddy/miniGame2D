package com.company;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            ViewManager manager = new ViewManager();
            stage = manager.getMainStage();
            stage.setTitle("TABOK LEBAH BERHADIAH");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
