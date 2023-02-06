package com.labcomm.easyrepeater;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LabCommEasyRepeater extends Application {
    Image icon = new Image(getClass().getResourceAsStream("laboratory.png"));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LabCommEasyRepeater.fxml"));
        VBox root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("LabCommEasyRepeater v0.1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
