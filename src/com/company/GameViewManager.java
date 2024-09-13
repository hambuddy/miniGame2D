package com.company;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.KeyValue;

import java.sql.*;

public class GameViewManager {

    private AnimationTimer gameTimer;
    private Timeline timeline;
    private IntegerProperty timeSeconds;



    public void playGame() {
        DbConnect connectDB = new DbConnect();
        Stage theStage = new Stage();

        theStage.setTitle("Click the Target!");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(1200, 700);
        Image background = new Image("background.jpg");

        root.getChildren().add(canvas);

        Circle targetData = new Circle(100, 100, 32);
        IntValue points = new IntValue(0);

        theScene.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        if (targetData.containsPoint(e.getX(), e.getY())) {
                            double x = 50 + 1000 * Math.random();
                            double y = 50 + 500 * Math.random();
                            targetData.setCenter(x, y);
                            points.value++;
                        } else
                            points.value = 0;
                    }
                });

        GraphicsContext gc = canvas.getGraphicsContext2D();



        Font theFont = Font.font("Comic Sans MS", FontWeight.BOLD, 24);
        gc.setFont(theFont);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        AnimatedImage lebah = new AnimatedImage();
        Image[] imageArray = new Image[6];
        for (int i = 0; i < 6; i++)
            imageArray[i] = new Image("lebah_" + i + ".png");
        lebah.frames = imageArray;
        lebah.duration = 0.100;

        //TIMER COUNT DOWN
        TimerGameCountDown timerGameCountDown = new TimerGameCountDown();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (timerGameCountDown.timeCounter > 0) {
                    timerGameCountDown.timeCounter--;
                }
                else if (timerGameCountDown.timeCounter == 0) {
                    gameTimer.stop();
                }
                else {
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                gc.drawImage(background, 0, 0);
                gc.drawImage(lebah.getFrame(t),
                        targetData.getX() - targetData.getRadius(),
                        targetData.getY() - targetData.getRadius());

                gc.setFill(Color.RED);

                String pointsText = "Points: " + points.value;
                gc.fillText(pointsText, 1000, 40);
                gc.strokeText(pointsText, 1000, 40);


                String timerText = "Time: " + timerGameCountDown.timeCounter;
                gc.fillText(timerText, 1000, 70);
                gc.strokeText(timerText, 1000, 70);

            }
        }.start();


        theStage.show();


        if  (timeline != null) {
            timeline.stop();
        }

        timeSeconds.set(timerGameCountDown.timeCounter);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(timerGameCountDown.timeCounter + 1), new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
        timeline.setOnFinished((e) -> {
            if (timerGameCountDown.timeCounter == 0) {
                theStage.close();
            }

        }); gameTimer.stop();


    }



}

