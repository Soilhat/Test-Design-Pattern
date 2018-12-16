package edu.insightr.gildedrose.controller;

import edu.insightr.gildedrose.Model.Inventory;
import edu.insightr.gildedrose.Model.Item;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
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
    @FXML
    BarChart barChartSellIn;
    @FXML
    CategoryAxis category;
    @FXML
    NumberAxis number;


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
        table.setItems(inv.getItems());
        table.getColumns().setAll(typeCol, nameCol, sellInCol, qualityCol);
        piechartFunction();
        barchartSellIn();
    }

    public void piechartFunction(){

        PieChart.Data s0 = new PieChart.Data("Aged Brie", inv.countItem()[0]);
        PieChart.Data s1 =  new PieChart.Data("Sulfuras", inv.countItem()[1]);
        PieChart.Data s2 = new PieChart.Data("Backstage Passes", inv.countItem()[2]);
        PieChart.Data s3 = new PieChart.Data("Conjured Mana Cake", inv.countItem()[3]);
        PieChart.Data s4 =  new PieChart.Data("Dexterity Vest", inv.countItem()[4]);
        PieChart.Data s5 =  new PieChart.Data("Elixir of the Mongose", inv.countItem()[5]);
        pie.setData(FXCollections.observableArrayList(s0, s1, s2, s3, s4, s5));

        pie.setTitle("Inventory");
    }

    public void barchartSellIn(){

        number.setLabel("SellIn");
        barChartSellIn.setTitle("jjjj");
        barChartSellIn.getXAxis().setLabel("Items");
        barChartSellIn.getYAxis().setLabel("SellIn");

        //barChartSellIn = new BarChart(category, number);
        //barChartSellIn.getXAxis().setLabel("aaaa");


        XYChart.Series dataSeries1 = new XYChart.Series();
        //dataSeries1.setName("2014");
        //dataSeries1.setName("2014");
        //XYChart.Data s1 = new XYChart.Data("Aged Brie", 100);
        XYChart.Data s0 = new XYChart.Data("Aged Brie", inv.countItem()[0]);
        XYChart.Data s1 =new XYChart.Data("Sulfuras", inv.countItem()[1]);
        XYChart.Data s2 =new XYChart.Data("Backstage Passes", inv.countItem()[2]);
        XYChart.Data s3 =new XYChart.Data("Conjured Mana cake", inv.countItem()[3]);
        XYChart.Data s4 =new XYChart.Data("Dexterity Vest", inv.countItem()[4]);
        XYChart.Data s5 =new XYChart.Data("Elixir of the Mongose", inv.countItem()[5]);
        //dataSeries1.getData().add(new XYChart.Data("Tablet"  , 23));
        //dataSeries1.getData().add(s1);
        dataSeries1.setData((FXCollections.observableArrayList(s0, s1, s2, s3, s4,s5)));
        //barChartSellIn.setData(dataSeries1);
        barChartSellIn.setData(FXCollections.observableArrayList(dataSeries1));

    }
    public void UpdateButton(){
        inv.updateQuality();
        fetchItem();
    }

    public void loadFileButton() {
        inv = new Inventory("gildedRosebis.json");
        fetchItem();
    }
}
