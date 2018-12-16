package edu.insightr.gildedrose.controller;

import edu.insightr.gildedrose.Model.Inventory;
import edu.insightr.gildedrose.Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;
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
    BarChart BarChartItems;
    @FXML
    BarChart barChartSellIn;


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
        barchartFunction();
        barchartSellIn();
    }

    private void piechartFunction(){
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Aged Brie", inv.countItem()[0]),
                        new PieChart.Data("Sulfuras", inv.countItem()[1]),
                        new PieChart.Data("Pass", inv.countItem()[2]),
                        new PieChart.Data("Conjured", inv.countItem()[3]),
                        new PieChart.Data("Vest", inv.countItem()[4]),
                        new PieChart.Data("Elixir", inv.countItem()[5]));
        pie.setData(pieChartData);
        pie.setLabelLineLength(1);
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
        BarChartItems.setData(FXCollections.observableArrayList(set1));
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
        piechartFunction();
    }
    public void loadFileButtonbis(){
        inv.ChargeItems("gildedRosebis.json");
        fetchItem();
        piechartFunction();
    }

    public void sellButtonCliked(){
        Item toSell = table.getSelectionModel().getSelectedItem();
        if(toSell != null) inv.SellItem(toSell);
        fetchItem();
        piechartFunction();
    }

}