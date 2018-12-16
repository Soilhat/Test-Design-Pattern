package edu.insightr.gildedrose.controller;

import edu.insightr.gildedrose.Model.Inventory;
import edu.insightr.gildedrose.Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class InventoryController implements Initializable {

    private Inventory inv;
    @FXML
    TableView<Item> table;
    @FXML
    PieChart pie;
    @FXML
    BarChart BarchartItems;
    @FXML
    MenuButton listItem;
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

    private void fetchItem(){
        TableColumn<Item, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Item, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Item, String> sellInCol = new TableColumn<>("SellIn");
        sellInCol.setCellValueFactory(new PropertyValueFactory<>("sellIn"));
        TableColumn<Item, String> qualityCol = new TableColumn<>("Quality");
        qualityCol.setCellValueFactory(new PropertyValueFactory<>("quality"));
        TableColumn<Item, String> creationCol = new TableColumn<>("Creation date");
        creationCol.setCellValueFactory(new PropertyValueFactory<>("creation_date"));
        table.setItems(inv.getItems());
        //noinspection unchecked
        table.getColumns().setAll(typeCol, nameCol, sellInCol, qualityCol, creationCol);
        piechartFunction();
        barchartFunction();
        barchartSellIn();
    }

    private void piechartFunction(){
        PieChart.Data s0 = new PieChart.Data("Aged Brie", inv.countItem()[0]);
        PieChart.Data s1 =  new PieChart.Data("Sulfuras", inv.countItem()[1]);
        PieChart.Data s2 = new PieChart.Data("Backstage Passes", inv.countItem()[2]);
        PieChart.Data s3 = new PieChart.Data("Conjured Mana Cake", inv.countItem()[3]);
        PieChart.Data s4 =  new PieChart.Data("Dexterity Vest", inv.countItem()[4]);
        PieChart.Data s5 =  new PieChart.Data("Elixir of the Mongose", inv.countItem()[5]);
        pie.setData(FXCollections.observableArrayList(s0, s1, s2, s3, s4, s5));
        pie.setTitle("Inventory");
    }

    @SuppressWarnings({"unchecked"})
    private void barchartFunction(){
        XYChart.Series set1 = new XYChart.Series<>();
        Map<String, Integer> dico = inv.itemCountPerDate();
        Set<Map.Entry<String, Integer>> setHm = dico.entrySet();
        Iterator<Map.Entry<String, Integer>> it = setHm.iterator();
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
        while(it.hasNext()){
            Map.Entry<String, Integer> e = it.next();
            data.add(new XYChart.Data(e.getKey(), e.getValue()));
        }
        set1.setData(data);
        BarchartItems.setData(FXCollections.observableArrayList(set1));
    }

    @SuppressWarnings({"unchecked"})
    private void barchartSellIn(){
        XYChart.Series set1 = new XYChart.Series<>();
        Map<String, Integer> dico = inv.countSellIn();
        Set<Map.Entry<String, Integer>> setHm = dico.entrySet();
        Iterator<Map.Entry<String, Integer>> it = setHm.iterator();
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
        while(it.hasNext()){
            Map.Entry<String, Integer> e = it.next();
            data.add(new XYChart.Data(e.getKey(), e.getValue()));
        }
        set1.setData(data);
        barChartSellIn.setData(FXCollections.observableArrayList(set1));
    }


    public void UpdateButton(){
        inv.updateQuality();
        fetchItem();
    }

    public void loadFileButton(){
        inv.ChargeItems("gildedRose.json");
        fetchItem();
    }
    public void loadFileButtonbis(){
        inv.ChargeItems("gildedRosebis.json");
        fetchItem();
    }
}