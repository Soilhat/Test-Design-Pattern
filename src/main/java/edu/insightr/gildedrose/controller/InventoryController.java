package edu.insightr.gildedrose.controller;

import edu.insightr.gildedrose.Model.Inventory;
import edu.insightr.gildedrose.Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    Inventory inv;
    @FXML
    TableView<Item> table;
    @FXML
    PieChart pie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inv = new Inventory("gildedRose.json");
        fetchItem();
    }

    public void fetchItem(){
        TableColumn typeCol = new TableColumn("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<Item,String>("type"));
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        TableColumn sellInCol = new TableColumn("SellIn");
        sellInCol.setCellValueFactory(new PropertyValueFactory<Item,String>("sellIn"));
        TableColumn qualityCol = new TableColumn("Quality");
        qualityCol.setCellValueFactory(new PropertyValueFactory<Item,String>("quality"));
        table.setItems(inv.getItems());
        table.getColumns().setAll(typeCol, nameCol, sellInCol, qualityCol);
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Grapefruit", 13),
                        new PieChart.Data("Oranges", 25),
                        new PieChart.Data("Plums", 10),
                        new PieChart.Data("Pears", 22),
                        new PieChart.Data("Apples", 30)
                );
        ;
        pie.getData().addAll(pieChartData);

    }

    public void UpdateButton(){
        inv.updateQuality();
        fetchItem();
    }

}
