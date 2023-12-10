package com.example.physicstest;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Timer;

public class Gravity {

    ArrayList<Rectangle> rectValues = new ArrayList<Rectangle>();

    ArrayList<PhysicsCube> allObjects = new ArrayList<PhysicsCube>();

    Gravity () {
        allObjects = new ArrayList();
    }

    public void addObject(PhysicsCube rect) {
        allObjects.add(rect);
    }

    public void turnOnGravity() {

        Timeline timeline = new Timeline();

        timeline.getKeyFrames().add(new KeyFrame(new Duration(5), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {

                allObjects.forEach((n) -> n.useForce(allObjects));
                allObjects.forEach((n) -> n.gravityMove());
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();

        }

        public ArrayList<Rectangle> fillArray() {
            allObjects.forEach((n) -> rectValues.add(rectValues.size(),n.rect));
            return rectValues;
        }



}
