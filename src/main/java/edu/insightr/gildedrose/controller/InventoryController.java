package edu.insightr.gildedrose.controller;

import edu.insightr.gildedrose.Model.Inventory;
import edu.insightr.gildedrose.Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
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

       PieChart.Data s0 = new PieChart.Data("Aged Brie", inv.countItem()[0]);
       PieChart.Data s1 =  new PieChart.Data("Sulfuras", inv.countItem()[1]);
       PieChart.Data s2 = new PieChart.Data("Backstage Passes", inv.countItem()[2]);
       PieChart.Data s3 = new PieChart.Data("Conjured Mana Cake", inv.countItem()[3]);
       PieChart.Data s4 =  new PieChart.Data("Dexterity Vest", inv.countItem()[4]);

        pie.getData().add(s0);
        pie.getData().add(s1);
        pie.getData().add(s2);
        pie.getData().add(s3);
        pie.getData().add(s4);
        pie.setTitle("Inventory");
        /*pie.setClockwise(true);
        //pie.setLabelLineLength(50);
        pie.setLabelsVisible(true);
        pie.setStartAngle(180);
        pie.setLegendSide(Side.RIGHT);*/
    }

    public void UpdateButton(){
        inv.updateQuality();
        fetchItem();
    }

}
