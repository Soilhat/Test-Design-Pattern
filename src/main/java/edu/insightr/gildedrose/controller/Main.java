package edu.insightr.gildedrose.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Inventory.fxml"));
            Scene scene = new Scene(root,862,604);

            /*chart.setTitle("Imported Fruits");
            root = new Group(chart);
            Scene scene2 = new Scene(root, 600, 400);*/
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }



    }


    public static void main(String[] args) throws ParseException {
        launch(args);
    }
}

