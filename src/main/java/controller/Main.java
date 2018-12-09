/*package controller;

import edu.insightr.gildedrose.Aged_Brie;
import edu.insightr.gildedrose.Inventory;
import edu.insightr.gildedrose.Item;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.text.TabableView;

public class Main extends Application {
    TableView<Item> table = new TableView<Item>();
    ObservableList<Item> data = FXCollections.observableArrayList(new Inventory().getItems());
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Inventory.fxml"));
            Scene scene = new Scene(root,600,500);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

        //TableView table = new TableView();
        table.setEditable(true);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        TableColumn sellInCol = new TableColumn("SellIn");
        sellInCol.setCellValueFactory(new PropertyValueFactory<Item,String>("sellIn"));
        TableColumn qualityCol = new TableColumn("Quality");
        qualityCol.setCellValueFactory(new PropertyValueFactory<Item,String>("quality"));

        table.setItems(data);
        table.getColumns().addAll(nameCol, sellInCol, qualityCol);
        StackPane root = new StackPane();
        root.getChildren().add(table);
        primaryStage.setScene(new Scene(root,600,250));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        //Student stu  = new Student(32, "hjj","hjhg",null,"hkj",7,"hg");
    }
}
*/
