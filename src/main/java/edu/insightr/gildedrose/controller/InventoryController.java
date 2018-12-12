package edu.insightr.gildedrose.controller;

import edu.insightr.gildedrose.Model.Inventory;
import edu.insightr.gildedrose.Model.Item;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    Inventory inv;
    @FXML
    TableView<Item> table;
    @FXML
    PieChart pie;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inv = new Inventory();
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
        TableColumn creationCol = new TableColumn("Creation date");
        creationCol.setCellValueFactory(new PropertyValueFactory<Item, Date>("creation_date"));
        table.setItems(inv.getItems());
        table.getColumns().setAll(typeCol, nameCol, sellInCol, qualityCol, creationCol);
        piechartFunction();
    }

    public void piechartFunction(){

        PieChart.Data s0 = new PieChart.Data("Aged Brie", inv.countItem()[0]);
        PieChart.Data s1 =  new PieChart.Data("Sulfuras", inv.countItem()[1]);
        PieChart.Data s2 = new PieChart.Data("Backstage Passes", inv.countItem()[2]);
        PieChart.Data s3 = new PieChart.Data("Conjured Mana Cake", inv.countItem()[3]);
        PieChart.Data s4 =  new PieChart.Data("Dexterity Vest", inv.countItem()[4]);
        pie.setData(FXCollections.observableArrayList(s0, s1, s2, s3, s4));

        pie.setTitle("Inventory");
    }

    public void barchartFunction(){
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data()

    }

    public void UpdateButton(){
        inv.updateQuality();
        fetchItem();
    }

    public void loadFileButton() {
        inv = new Inventory("gildedRose.json");
        fetchItem();
    }
}
