package com.example.physicstest;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;


public class PhysicsCube {

    public Rectangle rect;
    Scene scene;
    Stage stage;
    Robot rb = new Robot();
    Timeline t = new Timeline();
    double xForce = 0;
    double yForce = 0;
    int currentMouseX;
    int previousMouseX;

    int currentMouseY;
    int previousMouseY;
    Rotate r = new Rotate();

    double offsetX;
    double offsetY;


    PhysicsCube (Group group, int x, int y, Stage stage, Scene scene, Color c) {
        rect = new Rectangle(x,y,50,50);
        rect.setFill(c);
        group.getChildren().add(rect);
        this.scene = scene;
        this.stage = stage;



        rect.getTransforms().add(r);


        t.getKeyFrames().add(new KeyFrame(new Duration(5), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                currentMouseX = (int)rb.getMouseX();
                xForce =  xForce + ((currentMouseX-previousMouseX));
                previousMouseX = (int)rb.getMouseX();

                currentMouseY = (int)rb.getMouseY();
                yForce =  yForce + (currentMouseY-previousMouseY);
                previousMouseY = (int)rb.getMouseY();

//                r.setPivotX(rb.getMouseX()- stage.getX()+6);
//                r.setPivotY(rb.getMouseY()- stage.getY()+6);
//
//                System.out.println((int)stage.getX()+6 +", "+ (int)stage.getY());
//
//                r.setAngle(r.getAngle()+1);

//                r.setPivotX(rect.getX()+offsetX);
//                r.setPivotY(rect.getY()+offsetY);

            }
        }));
        t.setCycleCount(Timeline.INDEFINITE);

        rect.addEventHandler(MouseEvent.MOUSE_PRESSED, e ->{
            previousMouseX = (int)rb.getMouseX();
            previousMouseY = (int)rb.getMouseY();

            offsetX = (rb.getMouseX()- stage.getX())-rect.getX();
            offsetY = (rb.getMouseY()- stage.getY())-rect.getY();

            r.setPivotX(rb.getMouseX()- stage.getX());
            r.setPivotY(rb.getMouseY()- stage.getY());

            t.play();
        });

        rect.addEventHandler(MouseEvent.MOUSE_RELEASED, e ->{
            currentMouseX = 0;
            previousMouseX = 0;
            currentMouseY = 0;
            previousMouseY = 0;

//            r.setPivotX(rect.getX());
//            r.setPivotY(rect.getY());

            t.stop();
        });

    }

    public void gravityMove(){
        if(!isGrounded() && rect.getY()< (scene.getHeight()+ rect.getHeight())) {
            yForce = yForce+0.4;
        }else if(rect.getY() + rect.getHeight()> scene.getHeight()) {
            rect.setY(scene.getHeight()-rect.getHeight());
        }
    }

    public boolean isGrounded() {
        if(t.getStatus().toString().equals("RUNNING")) {
            return true;
        }else if(rect.getY() < scene.getHeight()-rect.getHeight()) {
            return false;
        }
        return true;
    }


    public void useForce(ArrayList<PhysicsCube> otherCubes) {



        //opposite force when colliding
        for(int i = 0; i < otherCubes.size(); i++) {
            if(!otherCubes.get(i).getRect().equals(this.rect) && rect.intersects(otherCubes.get(i).getRect().getX()-xForce, otherCubes.get(i).getRect().getY()-yForce,otherCubes.get(i).getRect().getWidth(),otherCubes.get(i).getRect().getHeight())) {
                otherCubes.get(i).addForce(xForce,yForce);
                xForce = -xForce/1.5;
                yForce = -yForce/1.5;
            }
        }
//        rect.getTransforms().remove(r);

        //actually moving shape
        rect.setX(xForce/2 + rect.getX());
        rect.setY(yForce/2 + rect.getY());

//        rect.getTransforms().add(r);

        //force after mouse release
        if(t.getStatus().toString().equals("STOPPED")) {
            xForce = xForce/1.02;
            yForce = yForce/1.02;

        }
        else{
            //force during mouse held down
            xForce = xForce/2;
            yForce = yForce/2;
        }
    }



    public void addForce(double horizontalForce, double verticalForce) {
        xForce+=horizontalForce;
        yForce+=verticalForce;
    }


    public Rectangle getRect() {
        return rect;
    }

}
