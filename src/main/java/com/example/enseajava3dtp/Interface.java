package com.example.enseajava3dtp;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Interface extends Application {
    public ArrayList<Flight> listOfFlight;
    public Translate tz;
    Double mousePosX = 0.0;
    Double mousePosY = 0.0;
    boolean test = true;


    public void start(Stage primaryStage) throws FileNotFoundException {

        primaryStage.setTitle("ENSEA 3D - tbeguin - Flight Radar");
        Earth root = new Earth();
        Pane pane = new Pane(root);
        Scene theScene = new Scene(pane, 600, 400,true);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(1.0);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(60.0);
        mouseEvent(pane);


        theScene.setCamera(camera);

        primaryStage.setScene(theScene);
        primaryStage.show();
    }

    public void mouseEvent(Node node) {
        node.setOnScroll((ScrollEvent event) -> {
            double zoomFactor = 1.05;
            double delta = event.getDeltaY();
            if (delta < 0)
                zoomFactor = 2.0 - zoomFactor;
            node.setScaleZ(node.getScaleZ() * zoomFactor);
        });
        node.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_RELEASED)
                test = true;
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                if (test)
                {
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                    test = false;
                }
                double deltaX = event.getSceneX() - mousePosX;
                double deltaY = event.getSceneY() - mousePosY;

                Rotate rX = new Rotate(-deltaX * 0.5, Rotate.Y_AXIS);
                Rotate rY = new Rotate(deltaY * 0.5, Rotate.X_AXIS);

                node.getTransforms().addAll(rX, rY);
                mousePosX = event.getSceneX();
                mousePosY = event.getSceneY();
            }
        });


    }

    public static void main(String[] args) {
        launch(args);
    }
}
