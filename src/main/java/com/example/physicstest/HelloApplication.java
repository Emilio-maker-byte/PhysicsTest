package com.example.physicstest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override

    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Group group = new Group();

        Gravity gravity = new Gravity();

        Scene scene = new Scene(group, 500, 500);

        PhysicsCube cube = new PhysicsCube(group, 240,230, stage, scene, Color.AQUA);
        PhysicsCube cube2 = new PhysicsCube(group, 5,20, stage, scene, Color.BISQUE);
        PhysicsCube cube3 = new PhysicsCube(group, 250,20, stage, scene, Color.BROWN);
        gravity.addObject(cube);
        gravity.addObject(cube2);
        gravity.addObject(cube3);

        gravity.turnOnGravity();

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}